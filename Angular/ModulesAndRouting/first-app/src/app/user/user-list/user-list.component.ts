import { Component, OnInit } from '@angular/core';
import { IUser } from '../../interfaces/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
  //changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserListComponent implements OnInit {

  users: IUser[] | undefined;
  errorLoadingUsers=false;

  constructor(public userService: UserService) { }

  ngOnInit(): void {
    this.loadUsers()
  }

  loadUsers(search? : string) {
    this.users=undefined;
    this.errorLoadingUsers=false;
    this.userService.loadUsers(search)
   // .pipe(catchError(()=> of([])))
    
    .subscribe(
      users => this.users = users, //next fn
      error => {
        this.errorLoadingUsers=true;
        console.error(error)
      }, //error fn
      () => console.log("load users stream completed")
      );
  }

  RelodButtonHandler() {
    this.loadUsers()
  }

  searchButtonHandler(searchInput: HTMLInputElement): void{

    const {value} = searchInput;
    this.loadUsers(value);
  }
}
