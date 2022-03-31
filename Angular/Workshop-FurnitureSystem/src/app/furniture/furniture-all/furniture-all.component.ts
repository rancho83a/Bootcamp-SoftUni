import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import IFurniture from 'src/app/model/furniture';
import { FurnitureServiceService } from '../service/furniture-service.service';


@Component({
  selector: 'app-furniture-all',
  templateUrl: './furniture-all.component.html',
  styleUrls: ['./furniture-all.component.css']
})

export class FurnitureAllComponent implements OnInit {
  //furnitures: Array<IFurniture>;
  furnitures$: Observable<Array<IFurniture>>;


  constructor(private furnitureService: FurnitureServiceService) { }

  ngOnInit() {
    //this.furnitureService.getAllFurnitures().subscribe(data=> this.furnitures=data);

    //Do with async PIPE

    setTimeout(() => this.furnitures$ = this.furnitureService.getAllFurnitures(), 3000);

  }

}


