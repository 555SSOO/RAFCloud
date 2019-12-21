import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../core/services/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  invalidLogin = false;

  constructor(private router: Router,
              private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  checkLogin() {
    this.authenticationService.authenticate(this.username, this.password).subscribe(isAuthenticated => {
      if ( isAuthenticated ) {
        sessionStorage.setItem('username', this.username);
        this.router.navigate(['']);
        this.invalidLogin = false;
      } else {
        this.invalidLogin = true;
      }
    });
  }

}
