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
    if (machine.active && machine.name) {
    this.machineService.createMachine(machine.active, machine.name, sessionStorage.getItem('username')).subscribe();
    } else {
      alert('Machine name and status are required');
    }
  }

}
