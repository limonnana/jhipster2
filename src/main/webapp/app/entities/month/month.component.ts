import { Component, OnInit } from '@angular/core';
import { Month, IMonth } from './month.model';
import { MonthService } from './month.service';
import { filter, map } from 'rxjs/operators';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { CollectionService } from '../../utils/collection.service';
import { UnitOfCalendar } from './unit.of.calendar.model';
import { KeyValue } from '@angular/common';
import { numberLiteralTypeAnnotation } from '@babel/types';

@Component({
  selector: 'jhi-month',
  templateUrl: './month.component.html',
  styles: []
})
export class MonthComponent implements OnInit {
  month: IMonth;
  imagePathNext: string;
  sortedMap: Map<string, UnitOfCalendar>;
  valueMap: UnitOfCalendar;
  theMap: (UnitOfCalendar)[][];
  list: (UnitOfCalendar)[];
  transformedList: [];
  costumComparator: number;

  constructor(
    private monthService: MonthService,
    protected jhiAlertService: JhiAlertService,
    private collectionService: CollectionService
  ) {}

  ngOnInit() {
    this.getCurrentMonth();
    this.imagePathNext = '../../content/images/next1.png';
  }

  calculateCustomComparator(a: KeyValue<number, (UnitOfCalendar)[]>, b: KeyValue<number, (UnitOfCalendar)[]>): number {
    let key1: number = +a.key;
    let key2: number = +b.key;
    let result = key1 > key2 ? 1 : key2 > key1 ? -1 : 0;
    return result;
  }

  getCurrentMonth() {
    this.monthService
      .getCurrentMonth()
      .pipe(
        filter((res: HttpResponse<IMonth>) => res.ok),
        map((res: HttpResponse<IMonth>) => res.body)
      )
      .subscribe(
        (res: IMonth) => {
          this.month = res;
          console.log('result: ' + this.month.name);
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  nextMonth() {
    this.monthService
      .getNextMonth(this.month.name, this.month.year)
      .pipe(
        filter((res: HttpResponse<IMonth>) => res.ok),
        map((res: HttpResponse<IMonth>) => res.body)
      )
      .subscribe(
        (res: IMonth) => {
          this.month = null;
          this.month = res;
          console.log('result: ' + this.month.name);
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
