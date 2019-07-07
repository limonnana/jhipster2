import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Month, IMonth } from './month.model';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { MonthDTO } from './monthDTO.model';
import { AccountService } from 'app/core';

type EntityResponseType = HttpResponse<IMonth>;

@Injectable({
  providedIn: 'root'
})
export class MonthService {
  public resourceUrl = SERVER_API_URL + 'api';
  login: string;
  mDTO: MonthDTO;

  constructor(protected http: HttpClient, private accountService: AccountService) {}

  getCurrentMonth(): Observable<EntityResponseType> {
    return this.http.get<IMonth>(`${this.resourceUrl}/month`, { observe: 'response' });
  }

  getMonth(currentMonth: string, currentYear: number, direction: number): Observable<EntityResponseType> {
    return this.http.get<IMonth>(`${this.resourceUrl}/nextMonth/${currentMonth}/${currentYear}/${direction}`, { observe: 'response' });
  }

  addEntity(currentYear: number, currentMonth: string, day: number, from: number, untill: number) {
    this.accountService.identity().then(account => {
      this.login = account.login;
      console.log('userName at service: ' + account.login);
    });
    this.mDTO = new MonthDTO(0, currentYear, currentMonth, day, this.login, from, untill);
    return this.http.post<IMonth>(`${this.resourceUrl}/addEntity`, this.mDTO, { observe: 'response' });
  }
}
