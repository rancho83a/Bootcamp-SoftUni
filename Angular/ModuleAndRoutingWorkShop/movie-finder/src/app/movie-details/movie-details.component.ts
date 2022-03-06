import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import IMovieDetails from '../interface/MovieDetails';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  movie!: IMovieDetails;
  id!: string;
  genres!: string;

  constructor(
    private activateRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.movie = this.activateRoute.snapshot.data['singleMovie'];


    this.genres = this.movie.genres.map(gen => Reflect.get(gen, 'name')).join(' ');


    //debugger;


    // this.id = this.activateRoute.snapshot.params['id'];

    //esli dinamicheski v komponente bydet menyatsa id => use Observal
    // const idDynamic = this.activateRoute.params
    //   .subscribe((params: Params) => {
    //     this.id = params['id']
    //   });

    // this.movieService.getMovieById(this.id)
    //   .pipe(
    //     // tap(data=> console.log(data))
    //   )
    //   .subscribe((data) => {
    //     this.movie = data;
    //     this.genres = this.movie.genres.map(gen => Reflect.get(gen, 'name')).join(' ');

    //   });
  }

}
