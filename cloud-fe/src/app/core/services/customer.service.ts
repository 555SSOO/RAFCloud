import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Customer} from '../models/customer.model';
import {User} from '../models/user.model';
import {SpringUrls} from './spring-urls';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private customerUrl: string;

  constructor(private http: HttpClient) {
    this.customerUrl = SpringUrls.CHILLFLIX_ENDPOINT + '/customer';
  }
  // TODO fix dis
  public findAll(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.customerUrl + '/get-all');
  }

  public getAllUsersForCustomer(customerUsername: string): Observable<User[]> {
    // TODO Security - add token
    const params = new HttpParams().set('customerUsername', customerUsername);
    return this.http.get<User[]>(this.customerUrl + '/get-users', {params});
  }

}
