import { UnitOfCalendar } from './unit.of.calendar.model';
import { MonthMapKey } from './month.map.key.model';

export interface IMonth {
  id?: number;
  name?: string;
  from?: number;
  untill?: number;
  year?: number;
  m?: Map<MonthMapKey, UnitOfCalendar>;
}

export class Month implements IMonth {
  constructor(
    public id?: number,
    public name?: string,
    public from?: number,
    public untill?: number,
    public year?: number,
    public m?: Map<MonthMapKey, UnitOfCalendar>
  ) {}
}
