import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { authGuard } from './auth/auth.guard';
import { loginRedirectGuard } from './auth/login-redirect.guard';
import { StudentDashboardComponent } from './dashboard/student-dashboard/student-dashboard.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [loginRedirectGuard],
  }, // when URL = /login, render LoginComponent
  {
    path: 'dashboard',
    component: StudentDashboardComponent,
    canActivate: [authGuard],
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // fallback route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)], // installs router into the app
  exports: [RouterModule], // makes it available for use
})
export class AppRoutingModule {}
