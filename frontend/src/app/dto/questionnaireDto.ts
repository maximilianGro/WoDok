export class QuestionnaireDto {
  constructor(
    public gender: string,
    public age: string,
    public notizen: string,
    public addtext: string,
    public location: [],
    public pain: string,
    public pain_amount: string,
    public pain_date: string,
    public bodypart: [],
    public symptom: [],

  ) {
  }
}
