import { Routes } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './core/services/auth.service';

export const authGuard = () => {
  const authService = inject(AuthService);
  return authService.isAuthenticated() ? true : ['/login'];
};

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./auth/login/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'products',
    canActivate: [authGuard],
    loadComponent: () => import('./product/product.component').then(m => m.ProductsComponent)
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' }
];

