import { Injectable } from '@angular/core';
import {of as observableOf} from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class PostsService {
  posts = [
    {
      id:1,
      userId: 1,
      title: "Post1"
    },
    {
      id:2,
      userId: 1,
      title: "Post2"
    },
    {
      id:3,
      userId: 3,
      title: "Post3"
    }
  ];
  constructor() { }

  load(){
    return observableOf(this.posts);
  }
}
