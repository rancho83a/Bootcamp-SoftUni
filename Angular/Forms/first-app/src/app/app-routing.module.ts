import { RouterModule, Routes } from "@angular/router";
import { AboutComponent } from "./about/about.component";
import { LoginComponent } from "./login/login.component";
import { NotFoundComponent } from "./not-found/not-found.component";
import { RegisterComponent } from "./register/register.component";
import { UserListComponent } from "./user/user-list/user-list.component";

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
        path:'about',
        component: AboutComponent
    },
    {
        path:'login',
        component: LoginComponent
    },
    {
        path:'register',
        component: RegisterComponent
    },
    {
        path:'**',
        component: NotFoundComponent
    }
];

export const AppRoutingModule = RouterModule.forRoot(route);
// export const AppRoutingModule = RouterModule.forRoot(route, {enableTracing: true});