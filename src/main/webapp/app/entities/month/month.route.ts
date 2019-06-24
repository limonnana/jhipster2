import { Route } from '@angular/router';

import { MonthComponent } from './month.component';

export const monthRoute: Route = {
  path: '',
  component: MonthComponent,
  data: {
    authorities: [],
    pageTitle: 'month.home.title'
  }
};
