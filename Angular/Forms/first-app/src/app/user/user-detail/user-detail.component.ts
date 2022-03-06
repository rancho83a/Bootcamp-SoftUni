import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap, tap } from 'rxjs';
import { IUser } from 'src/app/interfaces/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {

  user: IUser | undefined;

  constructor(private activatedRoot: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {

    //za slychau bez button "Next"
    // this.userService.loadUser(this.activatedRoot.snapshot.params['id']).subscribe(user=> {
    //   this.user=user;
    // })

    //za buttona Next

    this.activatedRoot.params.pipe(
      tap(this.user=undefined),//while waiting for responce - show loader, but user must be equal undefined - side effect 
      switchMap(({ id }) => this.userService.loadUser(id)))
      .subscribe(user => this.user = user)
  }
}