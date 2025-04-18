// auth.guard.ts
import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isLoggedIn()) {
    if (authService.getCurrentUser().role === 'teacher') {
      router.navigate(['/teacher-dashboard']);
      return false;
    }
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
