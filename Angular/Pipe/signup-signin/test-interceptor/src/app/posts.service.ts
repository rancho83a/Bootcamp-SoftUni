import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {of as observableOf} from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private http:HttpClient, private router:Router){

  }

  create(title: string, content: string){
    return this.http.post('feed/post/create', {title,content})
    .subscribe(data=> {
      this.router.navigate(['/home'])
    }, err=> console.error(err));
  }
  
}
