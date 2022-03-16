import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent  {

  phoneNumbers: string[] = ['+359', '+721', '+231'];
  @ViewChild('form')
  htmlForm!: NgForm;
  model={}

  constructor() {
   }

 
  register(formData:NgForm) : void{
    // if(this.htmlForm.invalid){
    //   return;
    // }
  //  this.htmlForm.reset();
    console.log(formData)

  }
}
