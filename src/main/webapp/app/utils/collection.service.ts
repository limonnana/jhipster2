import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CollectionService {
  theMap: any[];
  list: any[];
  mapa: Map<number, any>;

  constructor() {}

  sortMap(m: Map<number, any>): Map<number, any> {
    let keys;
    keys = Object.keys(m); //IterableIterator<number> = m.keys();
    let keysArray = Array.from(keys);

    // Array.from(m.entries()).forEach(entry => console.log('Key: ' + entry[0] + ' Value: ' + entry[1]));

    this.mapa = <Map<number, any>>m;

    console.log('theValue: ' + this.mapa.get(1));

    // this.mapa.forEach((value: any, key: number) => {
    //   console.log('key: ' + key, 'value: ' + value);
    // });

    // const orderedKeys = keysArray.sort();
    /*
    keys.forEach((def: number) => {
       console.log(' key:' + def);
       let key = def;
      // let value = this.mapa.get(key);
       this.list.push(key);
     //  this.list.push(value);
       this.theMap.push(this.list);

    })
    */
    //console.log('***************************************');
    // for (const [key, value] of m) {
    //  console.log('key: ' + key, 'value: ' + value);
    // }

    return m;
  }
}
