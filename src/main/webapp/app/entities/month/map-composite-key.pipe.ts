import { Pipe, PipeTransform } from '@angular/core';
import { Injectable } from '@angular/core';
import { UnitOfCalendar } from './unit.of.calendar.model';
import { MonthMapKey } from './month.map.key.model';

function makeKeyValuePair<K, V>(key: K, value: V): KeyValue<K, V> {
  return { key: key, value: value };
}

export interface KeyValue<K, V> {
  key: K;
  value: V;
}

@Injectable()
@Pipe({ name: 'mapCompositeKey', pure: false })
export class MapCompositeKeyPipe implements PipeTransform {
  private keyValues: Array<KeyValue<any, any>> = [];

  constructor() {}

  transform(value: any[][]): Array<KeyValue<MonthMapKey, UnitOfCalendar[]>> {
    let keyValues: Array<KeyValue<any, any>> = [];

    let key = Object.keys(value);
    for (let i = 0; i < key.length; i++) {
      let monthMapKey = value[i][0];
      let theList = value[i][1];
      let keyValue = makeKeyValuePair(monthMapKey, theList);

      keyValues.push(keyValue);
    }

    return keyValues;
  }
}

export function defaultComparator(a: KeyValue<MonthMapKey, (UnitOfCalendar)[]>, b: KeyValue<MonthMapKey, (UnitOfCalendar)[]>): number {
  const key1: number = +a.key.dayNumber;
  const key2: number = +b.key.dayNumber;
  const result = key1 > key2 ? 1 : key2 > key1 ? -1 : 0;
  return result;
}
