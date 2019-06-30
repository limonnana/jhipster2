import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Month, IMonth } from './month.model';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';

type EntityResponseType = HttpResponse<IMonth>;

@Injectable({
  providedIn: 'root'
})
export class MonthService {
  public resourceUrl = SERVER_API_URL + 'api';

  constructor(protected http: HttpClient) {}

  getCurrentMonth(): Observable<EntityResponseType> {
    return this.http.get<IMonth>(`${this.resourceUrl}/month`, { observe: 'response' });
  }

  getMonth(currentMonth: string, currentYear: number, direction: number): Observable<EntityResponseType> {
    return this.http.get<IMonth>(`${this.resourceUrl}/nextMonth/${currentMonth}/${currentYear}/${direction}`, { observe: 'response' });
  }
}
