import { Inject, Injectable } from '@angular/core';
import { myStringInjectionToken } from './app.module';
import { IUser } from './interfaces/user';

 @Injectable(
   //{providedIn: 'root'}
 )
export class UserService {
  users = [
    {
      name: "ivan 1",
      age: 21
    },
    {
      name: "ivan 2",
      age: 21
    },
    {
      name: "ivan 3",
      age: 22
    }
  ];


  constructor(@Inject(myStringInjectionToken) myString: string) { 
    console.log(myString);
  }

  addNewUserHandler(newUser: IUser): void {
    this.users= this.users.concat(newUser) // прави промяна по референция
    // this.users.push(newUser); // не прави промяна по референция -=> не трябва да се засича поради   changeDetection: ChangeDetectionStrategy.OnPush
  }

  // constructor() {
  //   setInterval(() => {
  //     this.users.push({
  //       name: "ivan 4",
  //       age: 44
  //     })
  //     console.log("new user added")
  //   }, 2000)
  // }
}
