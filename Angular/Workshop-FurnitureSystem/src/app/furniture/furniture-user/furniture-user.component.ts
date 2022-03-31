import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import IFurniture from 'src/app/model/furniture';
import { FurnitureServiceService } from '../service/furniture-service.service';

@Component({
  selector: 'app-furniture-user',
  templateUrl: './furniture-user.component.html',
  styleUrls: ['./furniture-user.component.css']
})
export class FurnitureUserComponent implements OnInit {

  furnitures$: Observable<Array<IFurniture>>;

  constructor(private furnitureService: FurnitureServiceService) { }

  ngOnInit() {

    this.furnitures$ = this.furnitureService.getUserFurniture();

  }

  deleteFurniture(id){
    this.furnitureService.deleteFurniture(id).subscribe(
      _ => {
      this.furnitures$=this.furnitureService.getUserFurniture();//update page
      }
    );
  }

}
