import { Directive, Input, OnDestroy } from '@angular/core';
import { AbstractControl, NgForm, NG_VALIDATORS, ValidationErrors, Validator } from '@angular/forms';
import { Subscription } from 'rxjs';
import { sameValueValidateFactory } from './login/same-value-validate-fn';

@Directive({
  //да се създава директивата само когато има ngModel && стойност appSameValue:
  selector: '[ngModel][appSameValue]', 
  //za da nakarame direktivata da e form-validation => put in providers: текущ dependency injector za view-то
  providers: [
    {
      provide: NG_VALIDATORS,
      useExisting: SameValueDirective,
      multi: true
    }
  ]
})
export class SameValueDirective implements Validator, OnDestroy {

  
  @Input() appSameValue = ""; // съдържа името на полето, с което ще срявняваме: "password" -> html: appSameValue="password"
  @Input() name!: string; //name="repeatPassword"
  otherControl!: AbstractControl;
  subscription!: Subscription; // за да се закачим за контролката на полето password, когато в него ще има промяна -ред 32

  constructor(private form: NgForm) { }

  validate(control: AbstractControl): ValidationErrors | null {  //method from implements class Validator
    const otherControl = this.form.controls[this.appSameValue]; // контролката на полето с което ще съпоставяме: password
    if(this.subscription){
      this.subscription.unsubscribe();
    }
    this.subscription=otherControl.valueChanges!.subscribe(()=>{// if have changes in password input field
      control.updateValueAndValidity({onlySelf: true}); //да се ръннат валидаторите за контролката repeat-password
    });

    return sameValueValidateFactory(this.name, otherControl, this.appSameValue)(control);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
