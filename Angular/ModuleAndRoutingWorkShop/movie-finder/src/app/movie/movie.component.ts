import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import IMovie from '../interface/Movie';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {


  @Input('movie')
  movie!: IMovie;
  imagePath! : string;

  @Output()
  clickButtonEmitter = new EventEmitter();



  constructor() { }

  ngOnInit(): void {
    this.imagePath='https://image.tmdb.org/t/p/w500'+this.movie.poster_path;
  }

  clickButton(){
    console.log("Button with id: "+this.movie.id+" is clicked from Child");
    this.clickButtonEmitter.emit(this.movie.id);

  }
}
