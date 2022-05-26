export class Registration {
  constructor(
    public email: string,
    public password: string,
    public doctor: boolean,
    public firstName: string,
    public lastName: string,
    public address: string,
    public city: string,
    public birthday: Date) {
  }
}
