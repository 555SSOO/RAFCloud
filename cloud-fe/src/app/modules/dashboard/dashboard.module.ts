import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';
import {MachineCreateComponent} from './machine-create/machine-create.component';
import {MachineSearchComponent} from './machine-search/machine-search.component';
import {VgCoreModule} from 'videogular2/compiled/src/core/core';
import {VgControlsModule} from 'videogular2/compiled/src/controls/controls';
import {BrowserModule} from '@angular/platform-browser';
import {DashboardComponent} from './dashboard.component';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [MachineSearchComponent, MachineCreateComponent, DashboardComponent],
  imports: [
    CommonModule,
    SharedModule,
    VgCoreModule,
    VgControlsModule,
    BrowserModule,
    FormsModule
  ]
})
export class DashboardModule { }
