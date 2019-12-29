import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SpringUrls} from './spring-urls';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly authenticationUrl: string;

  constructor(private http: HttpClient) {
    this.authenticationUrl = SpringUrls.BASE_URL + '/auth';
  }

  private backendAuthentication(username, password): Observable<string> {
    const params = new HttpParams().set('username', username).set('password', password);
    return this.http.get<string>(this.authenticationUrl, {params});
  }

  authenticate(username, password): Observable<string> {
    return this.backendAuthentication(username, password);
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem('username');
    return !(user === null);
  }

  logOut() {
    sessionStorage.removeItem('username');
  }

}
