import { Component } from '@angular/core';
import {MachineService} from '../../../core/services/machine.service';

@Component({
  selector: 'app-machine-create',
  templateUrl: './machine-create.component.html',
  styleUrls: ['./machine-create.component.css']
})
export class MachineCreateComponent {

  constructor(private machineService: MachineService) { }

  public createMachine(machine) {
    this.machineService.createMachine(machine.active, machine.name, sessionStorage.getItem('username')).subscribe();
  }

}
