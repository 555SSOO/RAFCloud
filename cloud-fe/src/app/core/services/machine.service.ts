import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SpringUrls} from './spring-urls';
import {Machine} from '../models/machine.model';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  private readonly machineUrl: string;

  constructor(private http: HttpClient) {
    this.machineUrl = SpringUrls.BASE_URL + '/machine';
  }

  public createMachine(active: string, machineName: string, username: string) {
    const params = new HttpParams().set('active', active).set('machineName', machineName).set('username', username);
    return this.http.post(this.machineUrl + '/create', params);
  }

  public getMachines(): Observable<Machine[]> {
    const params = new HttpParams().set('machineName', '').set('status', '').set('dateFrom', '').set('dateTo', '').set('username', '');
    return this.http.get<Machine[]>(this.machineUrl + '/search', {params});
  }

  public searchMachines(machineName: string, status: string, dateFrom: string, dateTo: string, username: string): Observable<Machine[]> {
    const params = new HttpParams().set('machineName', machineName)
                                   .set('status', status)
                                   .set('dateFrom', dateFrom)
                                   .set('dateTo', dateTo)
                                   .set('username', username);
    return this.http.get<Machine[]>(this.machineUrl + '/search', {params});
  }

}
