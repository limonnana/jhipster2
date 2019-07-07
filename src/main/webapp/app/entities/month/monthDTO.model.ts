export class MonthDTO {
  constructor(
    public id?: number,
    public year?: number,
    public name?: string,
    public day?: number,
    public userLogin?: string,
    public from?: number,
    public untill?: number
  ) {}
}
