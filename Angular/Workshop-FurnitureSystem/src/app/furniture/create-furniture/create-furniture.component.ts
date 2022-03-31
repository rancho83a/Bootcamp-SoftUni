import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FurnitureServiceService } from '../service/furniture-service.service';


@Component({
  selector: 'app-create-furniture',
  templateUrl: './create-furniture.component.html',
  styleUrls: ['./create-furniture.component.css']
})
export class CreateFurnitureComponent implements OnInit {
  form: FormGroup;

  constructor(private fb:FormBuilder, private furnitureService: FurnitureServiceService, private router:Router) { }

  ngOnInit() {
    this.form = this.fb.group({
      make: ['',[Validators.required, Validators.minLength(4)]],
      model: ['',[Validators.required, Validators.minLength(4)]],
      year: ['',[Validators.required, Validators.min(1950), Validators.max(2050)]],
      description: ['',[Validators.required, Validators.minLength(10)]],
      price: ['',[Validators.required, Validators.min(0.01)]],
      image:['',Validators.required],
      material:['', Validators.nullValidator]
    });
  }

  createFurniture(){
    this.furnitureService.createFurniture(this.form.value)
    .subscribe(data=>{
      this.router.navigate(['/furniture/all'])
    })
  }

  get f(){
    return this.form.controls;
  }

}
