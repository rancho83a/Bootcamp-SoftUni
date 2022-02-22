import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import IMovie from '../interface/Movie';
import { Observable } from '../../../node_modules/rxjs';


const BASE_URL = 'https://api.themoviedb.org/3/';
const POPULAR = BASE_URL +'movie/popular?'
const IN_THEATRE = BASE_URL+'discover/movie?'
const API_KEY = 'api_key=795af5e3bf6fca62b6621c30c89424d9';

@Injectable({ providedIn: 'root' })
export class MovieService {

  constructor(private http: HttpClient) { }


  getPopularMovies(): Observable<Array<IMovie>> {
    return this.http.get<Array<IMovie>>(POPULAR + API_KEY);
  }

  getInTheatreMovies(): Observable<Array<IMovie>> {
    return this.http.get<Array<IMovie>>(IN_THEATRE + API_KEY+'&with_release_type=2|3');
  }
}
