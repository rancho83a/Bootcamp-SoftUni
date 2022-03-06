import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { sameValueValidateFactory } from '../login/same-value-validate-fn';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnDestroy{

  form: FormGroup;
  subscription!: Subscription; // за да се закачим за контролката на полето password, когато в него ще има промяна -ред 32


  constructor(private fb: FormBuilder) { 

    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(4)]],
      repeatPassword: ['']
    });
   const sameValueValidate = sameValueValidateFactory('repeatPassword', this.form.get('password')!, 'password');
  
   this.subscription=this.form.get('password')!.valueChanges!.subscribe(()=>{// if have changes in password input field
    this.form.controls['repeatPassword'].updateValueAndValidity({onlySelf: true}); //да се ръннат валидаторите за контролката repeat-password
  });
  
   this.form.controls['repeatPassword'].setValidators([Validators.required, Validators.minLength(4), sameValueValidate]);
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }




}
