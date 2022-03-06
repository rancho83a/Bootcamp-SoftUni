import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovieDetailsComponent } from './movie-details/movie-details.component';
import { MovieSearchComponent } from './movie-search/movie-search.component';
import { MoviesComponent } from './movies/movies.component';
import { MovieListResolver } from './service/resolvers/movie-list.resolver';
import { SingleMovieResolver } from './service/resolvers/single-movie.resolver';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'movies'
  },
  {
    path: 'movies',
    component: MoviesComponent,
    resolve: {forkJoinMovies: MovieListResolver}
  },
  {
    path: 'movies/:id',
    component: MovieDetailsComponent,
    resolve : {singleMovie: SingleMovieResolver}
  },
  {
    path: 'movies/search',
    component: MovieSearchComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
