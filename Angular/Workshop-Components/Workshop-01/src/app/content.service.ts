import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPost, ITheme } from './shared/interfaces';
import { environment } from '../environments/environment';
const API_URL = environment.apiURL;

@Injectable(
  //{  providedIn: 'root'} put the same in providers in App.module.ts
)
export class ContentService {

  constructor(private http: HttpClient) { }

  loadThemes(){
    return this.http.get<ITheme[]>(`${API_URL}/comments`);
  }

  loadPosts(limit? :number){
    const query = limit ? `?limit=${limit}` : '';
    const res =this.http.get<IPost[]>(`${API_URL}/posts${query}`);
    return res
  }
}
