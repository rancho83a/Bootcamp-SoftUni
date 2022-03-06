import {Injectable} from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import IMovieDetails from 'src/app/interface/MovieDetails';
import { MovieService } from '../movie.service';

@Injectable()
export class SingleMovieResolver implements Resolve<IMovieDetails>{
    
    constructor( private movieService: MovieService){}
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){

        const id = route.params['id'];

        return this.movieService.getMovieById(id);
        
    }

}