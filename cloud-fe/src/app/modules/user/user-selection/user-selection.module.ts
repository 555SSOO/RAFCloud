import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import {UserSelectionComponent} from './user-selection.component';


@NgModule({
  declarations: [UserSelectionComponent],
  imports: [
    CommonModule,
    SharedModule
  ]
})
export class UserSelectionModule { }
