import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IReservation {
  id?: number;
  from?: Moment;
  untill?: Moment;
  user?: IUser;
}

export class Reservation implements IReservation {
  constructor(public id?: number, public from?: Moment, public untill?: Moment, public user?: IUser) {}
}
