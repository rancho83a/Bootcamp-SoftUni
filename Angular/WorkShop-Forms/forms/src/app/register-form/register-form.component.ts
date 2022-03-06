import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  phoneNumbers: string[] = ['+359', '+721', '+231'];

  constructor() { }

  ngOnInit(): void {
  }

  register(formData: any) : void{
   // if(form.invalid){return}
    console.log(formData);

  }
}
