export class Registration {
  constructor(
    public email: string,
    public password: string,
    public doctor: boolean,
    public firstName: string,
    public lastName: string,
    public street: string,
    public zip: string,
    public city: string,
    public country: string,
    public birthday: Date) {
  }
}
