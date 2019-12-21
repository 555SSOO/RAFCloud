import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import {ProductListingComponent} from './product-listing/product-listing.component';
import {ProductDetailsComponent} from './product-details/product-details.component';
import {VgCoreModule} from 'videogular2/compiled/src/core/core';
import {VgControlsModule} from 'videogular2/compiled/src/controls/controls';
import {BrowserModule} from '@angular/platform-browser';


@NgModule({
  declarations: [ProductListingComponent, ProductDetailsComponent],
  imports: [
    CommonModule,
    SharedModule,
    VgCoreModule,
    VgControlsModule,
    BrowserModule
  ]
})
export class ProductModule { }
