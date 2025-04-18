import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // required for ngModel
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module'; // your routing file
import { LoginComponent } from './auth/login/login.component'; // your login
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [AppComponent, LoginComponent], // your components
  imports: [BrowserModule, FormsModule, AppRoutingModule, HttpClientModule], // core modules
  bootstrap: [AppComponent],
})
export class AppModule {}
