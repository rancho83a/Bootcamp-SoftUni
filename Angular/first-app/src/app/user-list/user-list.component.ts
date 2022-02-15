import { ChangeDetectionStrategy, Component, Input, OnInit, Output } from '@angular/core';
import { IUser } from '../interfaces/user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserListComponent implements OnInit {

  //aaray of elements {name, age} with default value =[]
  @Input() userArray: IUser[] = [];

  constructor() { }

  ngOnInit(): void {
  }
}
