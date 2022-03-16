import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  constructor(private authService:AuthService, private router:Router) { }

  ngOnInit(): void {
  }
  submitHandler(form: NgForm){
    const [email,password] = form.value;
 this.authService.signin(email,password)
 .subscribe(data=>{
   localStorage.setItem('token', data.token);
   this.router.navigate(['/home']);
 }, 
 err => console.error(err));
  }

}
