import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {UserSelectionComponent} from './modules/user/user-selection/user-selection.component';
import {LoginComponent} from './modules/login/login.component';
import {LogoutComponent} from './modules/logout/logout.component';
import {AuthGuardService} from './core/services/auth-guard.service';
import {ProductListingComponent} from './modules/product/product-listing/product-listing.component';
import {ProductDetailsComponent} from './modules/product/product-details/product-details.component';

const routes: Routes = [
  {
    path: '',
    component: UserSelectionComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'logout',
    component: LogoutComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'product-list',
    component: ProductListingComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'product-details',
    component: ProductDetailsComponent,
    canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
