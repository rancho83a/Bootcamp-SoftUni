import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import IFurniture from 'src/app/model/furniture';
import { FurnitureServiceService } from '../service/furniture-service.service';


@Component({
  selector: 'app-furniture-details',
  templateUrl: './furniture-details.component.html',
  styleUrls: ['./furniture-details.component.css']
})
export class FurnitureDetailsComponent implements OnInit {
  furniture:IFurniture;


  constructor(private route: ActivatedRoute, //ActivatedRoute znae kade si v momenta, kade si bil predi tova
    private furnitureService:FurnitureServiceService
    ) { } 

  ngOnInit() {

    this.furniture=this.route.snapshot.data['singleFurniture'];

    //Subscribe in subscribe:

    // this.route.params.subscribe(data=>{
    //   let id = data['id'];
    //   this.furnitureService.getFurniture(id).subscribe(data=> this.furniture=data)
    // })


  }

}
