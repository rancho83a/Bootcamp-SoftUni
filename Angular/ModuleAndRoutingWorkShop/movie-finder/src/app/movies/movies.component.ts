import { Component, OnInit } from '@angular/core';
import IMovie from '../interface/Movie';
import { MovieService } from '../service/movie.service';




@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})


export class MoviesComponent  implements OnInit{

  popularMovie: Array<IMovie> =[];
  inTheatreMovie: Array<IMovie> =[];

  title!: string;

  message = null;

  constructor(private movieService: MovieService){ 
  }

  ngOnInit(){

    this.movieService.getPopularMovies().subscribe(defo => {

      this.popularMovie =  Reflect.get(defo,'results').slice(0,6);   
    });

    this.movieService.getInTheatreMovies().subscribe(defo => {

      this.inTheatreMovie =  Reflect.get(defo,'results').slice(0,6);   
    });
  }

  fromChild(event: any){
    console.log(event);

    this.message=event;
  }

}