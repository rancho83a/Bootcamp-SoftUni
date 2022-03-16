import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { ImageUrlVDirective } from './image-url-v.directive';
import { RegisterFormReaktiveComponent } from './register-form-reaktive/register-form-reaktive.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterFormComponent,
    ImageUrlVDirective,
    RegisterFormReaktiveComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
