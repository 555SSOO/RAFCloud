import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../../../core/services/customer.service';
import {User} from '../../../core/models/user.model';
import {UserService} from '../../../core/services/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-selection',
  templateUrl: './user-selection.component.html',
  styleUrls: ['./user-selection.component.css']
})
export class UserSelectionComponent implements OnInit {

  users: User[];

  constructor(private customerService: CustomerService,
              private userService: UserService,
              private router: Router) { }

  ngOnInit() {
    this.customerService.getAllUsersForCustomer(sessionStorage.getItem('username')).subscribe(data => {
      this.users = data;
    });
  }

  selectUser(userId: string) {
    this.userService.setCurrentUser(userId).subscribe(next => this.router.navigate(['product-list']));
  }

}
