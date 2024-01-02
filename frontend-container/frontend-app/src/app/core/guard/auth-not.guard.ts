import { CanActivateFn } from '@angular/router';
import {AuthService} from "../service/auth.service";
import {inject} from "@angular/core";

export const authNotGuard: CanActivateFn = (route, state) => {
  const authService: AuthService = inject(AuthService);
  if (!authService.isLogged()){
    return true;
  }
  authService.toHome();
  return false;
};
