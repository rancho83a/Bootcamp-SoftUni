import { Component, OnInit } from '@angular/core';
import { catchError, of } from 'rxjs';
import { IUser } from './interfaces/user';
import { UserService } from './user.service';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

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
