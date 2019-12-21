import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {SharedModule} from './shared/shared.module';
import {UserSelectionModule} from './modules/user/user-selection/user-selection.module';
import {LoginModule} from './modules/login/login.module';
import {LogoutModule} from './modules/logout/logout.module';
import {ProductModule} from './modules/product/product.module';

@NgModule({
  declarations: [
    AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    UserSelectionModule,
    LoginModule,
    LogoutModule,
    ProductModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
