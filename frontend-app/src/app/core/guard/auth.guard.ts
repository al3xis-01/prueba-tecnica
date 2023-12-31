import {ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot} from '@angular/router';
import {AuthService} from "../service/auth.service";
import {inject} from "@angular/core";

export const authGuard: (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => boolean = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  const authService: AuthService = inject(AuthService);
  if (authService.isLogged()){
    return true;
  }
  authService.toLogin();
  return false;
};
