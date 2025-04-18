import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent }, // when URL = /login, render LoginComponent
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // fallback route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)], // installs router into the app
  exports: [RouterModule], // makes it available for use
})
export class AppRoutingModule {}
