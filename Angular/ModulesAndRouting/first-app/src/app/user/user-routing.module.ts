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
        canActivate: [ParamsActivate]
    }
];

export const UserRoutingModule = RouterModule.forChild(route);