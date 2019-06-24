import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MonthComponent } from './month.component';
import { monthRoute } from './month.route';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [MonthComponent],
  imports: [RouterModule.forChild([monthRoute]), CommonModule]
})
export class MonthModule {}
