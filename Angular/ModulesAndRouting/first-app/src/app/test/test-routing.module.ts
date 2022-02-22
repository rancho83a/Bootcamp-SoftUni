import { RouterModule, Routes } from "@angular/router";
import { MainComponent } from "./main/main.component";
import { OneComponent } from "./one/one.component";
import { TwoComponent } from "./two/two.component";


const route: Routes = [
    {
        path: 'test',//sadarja se vav vseki dryg string, zatova => pathmatch
        component: MainComponent,
        children: [
            {
                path: '',
                pathMatch: 'full',
                redirectTo:'/test/one'
            },
            {
                path: 'one',
                component: OneComponent
            },
            {
                path: 'two',
                component: TwoComponent
            }
        ]
    }
];

export const TestRoutingModule = RouterModule.forChild(route);
