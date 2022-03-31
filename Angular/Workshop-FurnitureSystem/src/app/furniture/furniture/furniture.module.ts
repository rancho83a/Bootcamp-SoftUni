import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FurnitureAllComponent } from '../furniture-all/furniture-all.component';
import { CreateFurnitureComponent } from '../create-furniture/create-furniture.component';
import { FurnitureDetailsComponent } from '../furniture-details/furniture-details.component';
import { FurnitureUserComponent } from '../furniture-user/furniture-user.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FurnitureServiceService } from '../service/furniture-service.service';
import { RouterModule } from '@angular/router';
import { SingleFurnitureResolver } from '../service/SingleFurnitureResolver';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild([
      { path: '', pathMatch: 'full', redirectTo: 'home' },
      { path: 'all', component: FurnitureAllComponent},
      { path: 'user', component: FurnitureUserComponent },
      { path: 'details/:id', component: FurnitureDetailsComponent, resolve : {singleFurniture: SingleFurnitureResolver} },
      { path: 'create', component: CreateFurnitureComponent}
    ])

  ],
  declarations: [
    FurnitureAllComponent,
    CreateFurnitureComponent,
    FurnitureDetailsComponent,
    FurnitureUserComponent
  ],
  providers: [
    FurnitureServiceService

  ]
})
export class FurnitureModule { }
