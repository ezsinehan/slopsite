// component logic (class)
// this is the logic/controller(the class lifecycle, hooks, and methods)
import { Component } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  standalone: false,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) {}

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe({
      next: (res) => {
        console.log('login success:', res);
      },
      error: (err) => {
        console.error('login faild:', err);
      },
    });
  }
}
