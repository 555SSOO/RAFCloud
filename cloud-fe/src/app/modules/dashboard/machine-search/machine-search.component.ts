import {Component, OnInit} from '@angular/core';
import {MachineService} from '../../../core/services/machine.service';
import {Machine} from '../../../core/models/machine.model';

@Component({
  selector: 'app-machine-search',
  templateUrl: './machine-search.component.html',
  styleUrls: ['./machine-search.component.css']
})
export class MachineSearchComponent implements OnInit {

  machines: Machine[];

  constructor(private machineService: MachineService) {
  }

  ngOnInit() {
    this.machineService.getMachines().subscribe(data => {
      this.machines = data;
    });
  }

  searchMachines(searchParams) {

    this.machineService.searchMachines(searchParams.machineName,
                                       searchParams.status,
                                       searchParams.dateFrom,
                                       searchParams.dateTo,
                                       sessionStorage.getItem('username')).subscribe(data => {
      this.machines = data;
    });

  }
}
