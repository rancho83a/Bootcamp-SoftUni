import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { forkJoin } from 'rxjs';
import IMovie from 'src/app/interface/Movie';
import { MovieService } from '../movie.service';



@Injectable()
export class MovieListResolver implements Resolve<IMovie[]>{

    constructor(private movieService: MovieService) { }
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {


        return forkJoin(
            this.movieService.getDramaMovies(),
            this.movieService.getInTheatreMovies(),
            this.movieService.getKidsMovies(),
            this.movieService.getPopularMovies()
        )
    }
}