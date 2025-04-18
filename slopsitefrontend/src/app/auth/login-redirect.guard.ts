// auth/login-redirect.guard.ts
import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';

export const loginRedirectGuard: CanActivateFn = () => {
  const auth = inject(AuthService);
  const router = inject(Router);

  // If user is already logged in, redirect to dashboard
  if (auth.isLoggedIn()) {
    return router.createUrlTree(['/dashboard']);
  }

  // Otherwise, allow access to login page
  return true;
};
