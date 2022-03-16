import { Injectable } from '@angular/core';
import {delay, of as observableOf} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UsersService {
  users = [
    {
      id:1,
      firstName:'name1',
     lastName: "lastName1"
    },
    {
      id:2,
      firstName:'name2',
     lastName: "lastName2"
    },
    {
      id:3,
      firstName:'name3',
     lastName: "lastName3"
    }
  ];
  
  constructor() { }
load(){
  return observableOf(this.users)
  .pipe(delay(555))  ; // this.http.get('endpoint')
}


}
