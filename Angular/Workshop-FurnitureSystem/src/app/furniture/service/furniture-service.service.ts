import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import IFurniture from 'src/app/model/furniture';



const GET_ALL_FURNITURE = 'http://localhost:5000/furniture/all';
const createFurniture = 'http://localhost:5000/furniture/create';
const DETAILS_FURNITURE = "http://localhost:5000/furniture/details/";
const MINE_FURNITURE = "http://localhost:5000/furniture/user";
const DELETE_FURNITURE = "http://localhost:5000/furniture/delete/"

@Injectable({
  providedIn: 'root'
})

export class FurnitureServiceService {


  constructor(private http: HttpClient) { }

  createFurniture(data){
    return this.http.post(createFurniture, data);
  }

  getAllFurnitures(): Observable<Array<IFurniture>>{
    return this.http.get<Array<IFurniture>>(GET_ALL_FURNITURE);
  }

  getFurniture(id) : Observable<IFurniture>{
    return this.http.get<IFurniture>(DETAILS_FURNITURE+id);
  }

  getUserFurniture(): Observable<Array<IFurniture>>{
    return this.http.get<Array<IFurniture>>(MINE_FURNITURE);
  }

  deleteFurniture(id): Observable<any>{
    return this.http.delete(DELETE_FURNITURE + id);
  }
}
