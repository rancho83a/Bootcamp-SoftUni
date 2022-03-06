import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent { 

  isActive = true;


  toggleActive(){
    this.isActive =!this.isActive;
  }
}
