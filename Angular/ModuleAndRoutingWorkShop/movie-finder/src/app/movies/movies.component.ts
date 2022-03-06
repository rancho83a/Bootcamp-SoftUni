import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import IMovie from '../interface/Movie';


@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})

export class MoviesComponent implements OnInit, OnDestroy {

  popularMovie: Array<IMovie> = [];
  inTheatreMovie: Array<IMovie> = [];
  kidsMovie: Array<IMovie> = [];
  dramaMovie: Array<IMovie> = [];

  title!: string;

  message = null;

  dramaSubscription!: Subscription;

  constructor( private activateRoute: ActivatedRoute) {
  }


  //при навигиране на друга страница - subscribe си остава в рам-паметта на браузера, => ще им ликове, трябва да се unsubscribe при
  // уничтожаване на компонентата -> в undestroy (moje chrez Async pipe)
  ngOnDestroy(): void {
    // this.dramaSubscription.unsubscribe();
  }

  ngOnInit() {

    const [drama, theatre, kids, popular]= this.activateRoute.snapshot.data['forkJoinMovies'];


    this.dramaMovie = drama;
    this.inTheatreMovie = theatre;
    this.kidsMovie = kids;
    this.popularMovie = popular;
  

    // this.dramaSubscription = this.movieService.getPopularMovies().subscribe(defo => {

    //   this.popularMovie = defo;
    // });

    // this.movieService.getInTheatreMovies().subscribe(defo => {

    //   this.inTheatreMovie = defo;
    // });

    // this.movieService.getKidsMovies().subscribe(defo => this.kidsMovie = defo);

    // this.movieService.getDramaMovies().subscribe(defo => {

    //   this.dramaMovie = defo;
    // });


  }

  fromChild(event: any) {
    console.log(event);

    this.message = event;
  }

}