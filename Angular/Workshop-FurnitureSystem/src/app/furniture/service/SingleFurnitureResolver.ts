import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import IFurniture from "src/app/model/furniture";
import { FurnitureServiceService } from "./furniture-service.service";

@Injectable()
export class SingleFurnitureResolver implements Resolve<IFurniture>{
    
    constructor( private furnitureService: FurnitureServiceService){}
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){

        const id = route.params['id'];

        return this.furnitureService.getFurniture(id);
        
    }

}