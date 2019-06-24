import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { Starter03SharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [Starter03SharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [Starter03SharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Starter03SharedModule {
  static forRoot() {
    return {
      ngModule: Starter03SharedModule
    };
  }
}
