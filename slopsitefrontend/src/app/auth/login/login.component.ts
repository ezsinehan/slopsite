// component logic (class)
// this is the logic/controller(the class lifecycle, hooks, and methods)
import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  standalone: false,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  isLoading = false;
  errorMsg = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    if (!this.username || !this.password) {
      this.errorMsg = 'Username and password required';
      return;
    }

    this.isLoading = true;
    this.errorMsg = '';

    this.authService.login(this.username, this.password).subscribe({
      next: (res) => {
        this.isLoading = false;
        console.log(`welcome ${res.name}`);
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.isLoading = false;
        this.errorMsg = 'login failed please try again';
        console.error('login failed:', err);
      },
    });
  }
}
