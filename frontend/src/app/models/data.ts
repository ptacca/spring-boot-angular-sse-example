export class Data {

  public name: string;
  public value: number;
  public timestamp: Date;

  constructor(name: string, value: number, timestamp: Date) {
    this.name = name;
    this.value = value;
    this.timestamp = timestamp;
  }
}
