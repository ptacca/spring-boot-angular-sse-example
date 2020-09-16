import {Component, OnInit} from '@angular/core';
import {EventService} from './services/event.service';
import {Event} from './models/event';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  public events: Event[];
  public eventLimit: number;
  public customEventName: string;
  public eventSubscriptionUrl: string;

  constructor(private eventService: EventService) {
    this.events = [];
    this.eventLimit = 10;
    this.customEventName = 'custom';
    this.eventSubscriptionUrl = 'http://localhost:3535/events/subscribe';
  }

  ngOnInit(): void {
    this.subscribeAndGetEvents();
  }

  subscribeAndGetEvents(): void {
    this.eventService.subscribe(this.eventSubscriptionUrl);
    console.log('Event source is subscribed.');

    this.eventService.getEvents(this.customEventName).subscribe((event: Event) => {
      console.log('Event is got.');

      this.events.unshift(event);
      console.log('Event is added to event list.');
    });

    if (this.events.length >= this.eventLimit) {
      console.log('Event list limit exceeded.');

      this.eventService.unsubscribe();
      console.log('Event source is unsubscribed.');
    }
  }

  filterEventsByType(type: 'NOTIFICATION' | 'TOAST'): Event[] {
    return this.events.filter(value => value.type === type);
  }
}
