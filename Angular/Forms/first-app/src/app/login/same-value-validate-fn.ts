import { AbstractControl } from "@angular/forms";

export function sameValueValidateFactory(
    controlName: string,
    otherControl: AbstractControl,
    otherControlName: string
) {
    return function sameValueValdate(control: AbstractControl) {
        return control.value !== otherControl.value ? {
            // if don`t match return object with values of both fields
            sameValue: {
                [otherControlName]: otherControl.value,
                [controlName]: control.value
            }
        } : null;// if match 
    }
}