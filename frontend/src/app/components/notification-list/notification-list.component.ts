import {Component, Input} from '@angular/core';
import {Event} from '../../models/event';

@Component({
  selector: 'app-notification-list',
  templateUrl: './notification-list.component.html'
})
export class NotificationListComponent {

  @Input() notifications: Event[];

  constructor() {
    this.notifications = [];
  }
}
