import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import IMovie from '../interface/Movie';
import { MovieService } from '../service/movie.service';

@Component({
  selector: 'app-movie-search',
  templateUrl: './movie-search.component.html',
  styleUrls: ['./movie-search.component.css']
})
export class MovieSearchComponent implements OnInit {

  searchedMovies!: IMovie[];

  query!: string;

  constructor(private activateRoute: ActivatedRoute, private movieService: MovieService) { }

  ngOnInit() {

    //const query = this.activateRoute.snapshot.queryParams['search'];
    
    this.activateRoute.queryParams.subscribe((params: Params)=> {
      this.query = params['search'];

      this.movieService.searchMovie(this.query)
      .subscribe((data) => {

        this.searchedMovies = Reflect.get(data, 'results');
      });


    })
    console.log("from movie-search " + this.query);

  }

}
