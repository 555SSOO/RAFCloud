import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import {FormsModule} from '@angular/forms';
import {LogoutComponent} from './logout.component';


@NgModule({
  declarations: [LogoutComponent],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule
  ]
})
export class LogoutModule { }
