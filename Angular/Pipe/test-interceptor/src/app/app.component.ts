import { Component, OnDestroy } from '@angular/core';
import { PostsService } from './posts.service';
import { UsersService } from './users.service';
import { Observable, Subscription, zip as zipObservable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy {
  now = new Date();

  obj = {
    firstName: 'name1',
    lastName: "lastName1"
  };

  users!: Array<Object>;
  posts!: Array<any>;
  // subscription : Subscription | undefined ;


  users$: Observable<Array<any>>;
  posts$: Observable<Array<any>>;


  constructor(private userService: UsersService, private postService: PostsService,
    private http: HttpClient
  ) {

    this.http.get('https://jsonplaceholder.typicode.com/todos').subscribe((todos) => console.log(todos));
    this.users$ = this.userService.load();
    this.posts$ = this.postService.load();


    // this.subscription = zipObservable(this.userService.load(),this.postService.load()).subscribe(([users,posts])=> {
    //   this.users=users;
    //   this.posts=posts;
    // });

    // userService.load().subscribe(users=> this.users=users);
    // postService.load().subscribe(posts=> this.posts=posts);
  }
  ngOnDestroy(): void {
    // this.subscription?.unsubscribe();
  }


  //pipe instead this code

  // getUserPostCount(userId: number){

  //   return this.posts!.reduce((acc,curr)=> {
  //   if(curr.userId===userId){
  //     return acc+1;
  //   }
  //   return acc;
  // },0)
  // }

  // clickHandler(){
  //   this.posts$=this.posts$.concat( {
  //     id:3,
  //     userId: 1,
  //     title:'post4'
  //   });
  //   debugger
  //   console.log(this.posts);
  // }
}