import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import IMovie from '../interface/Movie';
import { map } from '../../../node_modules/rxjs';
import IMovieDetails from '../interface/MovieDetails';


const BASE_URL = 'https://api.themoviedb.org/3/';
const POPULAR = BASE_URL + 'movie/popular?'
const IN_THEATRE = BASE_URL + 'discover/movie?'
const API_KEY = 'api_key=795af5e3bf6fca62b6621c30c89424d9';
const API_KEY_ALT = '?api_key=795af5e3bf6fca62b6621c30c89424d9';
const KIDS = BASE_URL + 'discover/movie?certification_country=US&certification.lte=G&sort_by=popularity.desc&';
const DRAMA = BASE_URL + 'discover/movie?with_genres=18&primary_release_year=2021&'

@Injectable({ providedIn: 'root' })
export class MovieService {

  constructor(private http: HttpClient) { }


  // getPopularMovies(): Observable<Array<IMovie>> {
  //   return this.http.get<Array<IMovie>>(POPULAR + API_KEY);
  // }

  getPopularMovies() {
    return this.http.get<Array<IMovie>>(POPULAR + API_KEY)
      .pipe(map((data) => {
        return Reflect.get(data, 'results').slice(0, 6)
      }));
  }

  getInTheatreMovies() {
    return this.http.get<Array<IMovie>>(IN_THEATRE + API_KEY + '&with_release_type=2|3')
      .pipe(map((data) => {
        return Reflect.get(data, 'results').slice(0, 6)
      }));
  }

  getKidsMovies() {
    return this.http.get<Array<IMovie>>(KIDS + API_KEY)
      .pipe(
        // tap((value)=> console.log(value)),
        map((data) => {
          return Reflect.get(data, 'results').slice(0, 6)
        })
        // tap((value)=> console.log(value)),
      );
  }

  getDramaMovies() {
    return this.http.get<Array<IMovie>>(DRAMA + API_KEY)
      .pipe(map((data) => {
        return Reflect.get(data, 'results').slice(0, 6)
      }));
  }

  getMovies(url: string) {

    return this.http.get<IMovie[]>(url)
      .pipe(map((data) => {
        return Reflect.get(data, 'results').slice(0, 6)
      }));
  }


  getMovieById(id: string) {
    return this.http.get<IMovieDetails>(BASE_URL + `movie/${id}` + API_KEY_ALT)
  }


  searchMovie(query: string) {
    const url = BASE_URL + 'search/movie' + API_KEY_ALT + `&query=${query}`;
    return this.http.get<IMovie[]>(url);
  }
}
