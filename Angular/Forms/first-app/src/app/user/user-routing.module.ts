import { RouterModule, Routes } from "@angular/router";
import {ParamsActivate} from '../core/guards/params.activate'

import { UserDetailComponent } from "./user-detail/user-detail.component";
import { UserListComponent } from "./user-list/user-list.component";

const route: Routes = [
    {
        path:'',//sadarja se vav vseki dryg string, zatova => pathmatch
        pathMatch: 'full',
        redirectTo: 'user-list'
    },
    {
        path:'user-list',
        component: UserListComponent
    },
    {
        path:'user-detail/:id',
        component: UserDetailComponent,
        canActivate: [ParamsActivate],
        //посочваме кой точно параметр Гардът да гледа, ако ще го има - значи ще бъда допуснат до конкретния рут
        data: {
            paramsActivate: ['id'],
            paramsActivateRedirectUrl: '/user-list'
        }
    }
];

export const UserRoutingModule = RouterModule.forChild(route);