import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SpringUrls} from './spring-urls';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly userUrl: string;

  constructor(private http: HttpClient) {
    this.userUrl = SpringUrls.BASE_URL + '/user';
  }
  public setCurrentUser(userId: string) {
    sessionStorage.setItem('userId', userId);
    return this.http.post(this.userUrl + '/set-current-user', userId);
  }
  public getCurrentUserId(): string {
    return sessionStorage.getItem('userId');
  }
}
