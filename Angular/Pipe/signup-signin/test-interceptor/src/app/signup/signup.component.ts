import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private authService: AuthService, private router:Router) { }

  ngOnInit(): void {
  }

  submitHandler(form: NgForm){

    console.log(form.value);
    const [password,email, name] = form.value;

    this.authService.signup(password,name,email)
    .subscribe( _ => {
      this.router.navigate(['signin']);
    },
     err => { console.error(err)});
  }
}
