import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {SpringUrls} from './spring-urls';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly authenticationUrl: string;

  constructor(private http: HttpClient, private router: Router) {
    this.authenticationUrl = SpringUrls.CHILLFLIX_ENDPOINT + '/auth';
  }

  private backendAuthentication(username, password): Observable<boolean> {
    const params = new HttpParams().set('username', username).set('password', password);
    return this.http.get<boolean>(this.authenticationUrl, {params});
  }

  authenticate(username, password): Observable<boolean> {
    return this.backendAuthentication(username, password);
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem('username');
    return !(user === null);
  }

  isUserOnSelectionPage() {
    return this.router.url === '/';
  }

  logOut() {
    sessionStorage.removeItem('username');
  }

}
