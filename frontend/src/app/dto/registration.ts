export class Registration {
  constructor(
    public email: string,
    public password: string,
    public doctor: boolean,
    public firstname: string,
    public lastname: string,
    public address: string,
    public city: string,
    public birthday: Date) {
  }
}
