import {EventEmitter, Injectable, Output} from '@angular/core';
import {Observable, of, Subject} from 'rxjs';
import {Event} from '../models/event';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, first, tap} from 'rxjs/operators';
import {EventSourcePolyfill} from 'event-source-polyfill';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private eventSource: EventSource;
  private readonly eventSourceOptions = {};
  private readonly httpHeaders = {};

  @Output() onOpen = new EventEmitter();
  @Output() onError = new EventEmitter();
  @Output() onClose = new EventEmitter();
  @Output() onEventSent = new EventEmitter();
  @Output() onEventGot = new EventEmitter();

  constructor(private httpClient: HttpClient) {
    this.eventSourceOptions = {
      withCredentials: true
    };

    this.httpHeaders = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json');
  }

  public subscribe(subscribeUrl): void {
    const EventSource = EventSourcePolyfill || EventSourcePolyfill;

    this.eventSource = new EventSource(subscribeUrl, this.eventSourceOptions);
    console.log('Event source is created.');

    this.eventSource.onopen = () => {
      this.onOpen.emit();
      console.log('Event source is opened.');
    };

    this.eventSource.onmessage = (messageEvent: MessageEvent) => {
      console.log('New message is got.');

      const event: Event = JSON.parse(messageEvent.data);
      console.log('Message is converted to Event.');

      this.onEventGot.emit(event);
      console.log('New event is got.');
    };

    this.eventSource.onerror = (error: ErrorEvent) => {
      this.onError.emit(error);
      console.log('Event source has error.');

      this.unsubscribe();
    };
  }

  public unsubscribe(): void {
    this.eventSource.close();
    console.log('Event source is closed.');

    this.onClose.emit();
  }

  public getEvents(eventName: string): Subject<Event> {
    const eventSubject = new Subject<Event>();

    this.eventSource.addEventListener(eventName, (messageEvent: MessageEvent) => {
      console.log('New message is got.');

      const event: Event = JSON.parse(messageEvent.data);
      console.log('Message is converted to Event.');

      this.onEventGot.emit(event);
      console.log('New event is got.');

      eventSubject.next(event);
    });

    return eventSubject;
  }

  public sendEvent(sendUrl: string, event: Event): Observable<any> {
    const httpOptions = {
      headers: this.httpHeaders
    };

    return this.httpClient.post<Event>(sendUrl, event, httpOptions).pipe(
      first(),
      tap(response => {
        this.onEventSent.emit(event);
        console.log('Event is sent.');
      }),
      catchError(error => {
        this.onError.emit(error);
        console.log('Event source has error.');

        this.unsubscribe();

        return of(error);
      })
    );
  }
}
