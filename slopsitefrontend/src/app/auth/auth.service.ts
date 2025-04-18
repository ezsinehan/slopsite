import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private currentUser: User | null = null;

  private baseUrl = 'http://localhost:8080/auth';
  constructor(private http: HttpClient) {
    const savedUser = localStorage.getItem('user');
    if (savedUser) {
      this.currentUser = JSON.parse(savedUser);
    }
  }

  login(username: string, password: string): Observable<User> {
    return this.http
      .post<User>(
        `${this.baseUrl}/login`,
        JSON.stringify({ username: username, password: password }),
        { headers: { 'Content-Type': 'application/json' } }
      )
      .pipe(
        tap((user: User) => {
          this.currentUser = user;
          console.log('current user:', this.currentUser);
          localStorage.setItem('user', JSON.stringify(user));
        })
      );
  }

  getCurrentUser(): any {
    return this.currentUser;
  }

  isLoggedIn(): boolean {
    return this.currentUser !== null;
  }

  logout(): void {
    this.currentUser = null;
    localStorage.removeItem('user');
  }
}
