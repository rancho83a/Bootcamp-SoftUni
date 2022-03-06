import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, UrlTree } from "@angular/router";


@Injectable()
export class ParamsActivate implements CanActivate {
    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot): boolean | UrlTree {
        const { data: { paramsActivate, paramsActivateRedirectUrl } } = route;

        // (Array condition - for reduce don`t exception)      //moje da dostapim konkretniya root
        if (!paramsActivate || paramsActivate.length === 0 || !Array.isArray(paramsActivate)) { return true }

        //check if our root contains params which we put in activateParams(data) -
        const hasAllRouteParams = paramsActivate.reduce((acc, currentParam) => {

            return !!route.params[currentParam] && acc
        }, true);

        if (hasAllRouteParams) { return true } // if true - go to root
        //else redirect:

       return this.router.parseUrl(paramsActivateRedirectUrl || '/');
    }

}