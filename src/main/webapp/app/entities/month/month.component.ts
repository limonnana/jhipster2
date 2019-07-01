import { Component, OnInit } from '@angular/core';
import { IMonth } from './month.model';
import { MonthService } from './month.service';
import { filter, map } from 'rxjs/operators';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { JhiAlertService } from 'ng-jhipster';

@Component({
  selector: 'jhi-month',
  templateUrl: './month.component.html',
  styles: []
})
export class MonthComponent implements OnInit {
  month?: IMonth;
  imagePathNext: string;
  imagePathPrevious: string;
  counterIsBiggerThan0 = false;
  counter = 0;

  constructor(private monthService: MonthService, protected jhiAlertService: JhiAlertService) {}

  ngOnInit() {
    this.getCurrentMonth();
    this.imagePathNext = '../../content/images/next4.png';
    this.imagePathPrevious = '../../content/images/previous3.png';
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

  nextMonth(direction: number) {
    this.changeCounter(direction);
    if (this.counterIsBiggerThan0) {
      this.monthService
        .getMonth(this.month.name, this.month.year, direction)
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
    } else {
      this.getCurrentMonth();
    }
  }

  addEntity(day: number) {
    this.month.name;
    this.month.year;
    this.monthService
      .addEntity(this.month.name, this.month.year, day)
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

  removeEntity(day: number) {
    this.month.name;
    this.month.year;
    console.log('Remove Day is: ' + day);
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  changeCounter(i: number): boolean {
    this.counter = this.counter + i;
    if (this.counter > 0) {
      this.counterIsBiggerThan0 = true;
    } else {
      this.counterIsBiggerThan0 = false;
    }
    return this.counterIsBiggerThan0;
  }
}
