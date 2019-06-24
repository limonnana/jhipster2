import { Component, OnInit } from '@angular/core';
import { Month, IMonth } from './month.model';
import { MonthService } from './month.service';
import { filter, map } from 'rxjs/operators';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

@Component({
  selector: 'jhi-month',
  templateUrl: './month.component.html',
  styles: []
})
export class MonthComponent implements OnInit {
  month: IMonth;

  constructor(private monthService: MonthService, protected jhiAlertService: JhiAlertService) {}

  ngOnInit() {
    this.getCurrentMonth();
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
          // data => {
          //    this.userList = data;
          // });
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
