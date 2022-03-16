import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-form-reaktive',
  templateUrl: './register-form-reaktive.component.html',
  styleUrls: ['./register-form-reaktive.component.css']
})
export class RegisterFormReaktiveComponent implements OnInit {

  form!:FormGroup;

  constructor(private fb:FormBuilder) { }

  ngOnInit(): void {
    this.form=this.fb.group({
      //2-ri parametr v array, 3ti-paramt - za async
      fullName: ['', [Validators.required, Validators.pattern(/[A-Z][a-z]+\s[A-Z][a-z]+/)]],
      email: ['', Validators.required, Validators.email],

    })
  }

  register(){
    console.log(this.form);
  }

  get f(){
    return this.form.controls;
  }
}
