export class Event {

  public name: string;
  public description: string;
  public timestamp: Date;
  public type: 'NOTIFICATION' | 'TOAST';
  public status: 'INFO' | 'WARNING' | 'SUCCESS' | 'ERROR';
  public priority: 'UNDEFINED' | 'TECHNICAL' | 'LOW' | 'MEDIUM' | 'HIGH';
  public data: any;

  constructor(name: string,
              description: string,
              timestamp: Date,
              type: 'NOTIFICATION' | 'TOAST',
              status: 'INFO' | 'WARNING' | 'SUCCESS' | 'ERROR',
              priority: 'UNDEFINED' | 'TECHNICAL' | 'LOW' | 'MEDIUM' | 'HIGH',
              data: any) {
    this.name = name;
    this.description = description;
    this.timestamp = timestamp;
    this.type = type;
    this.status = status;
    this.priority = priority;
    this.data = data;
  }
}
