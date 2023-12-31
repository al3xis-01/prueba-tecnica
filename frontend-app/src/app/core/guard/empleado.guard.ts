import { CanActivateFn } from '@angular/router';
import {AuthService} from "../service/auth.service";
import {inject} from "@angular/core";

export const empleadoGuard: CanActivateFn = (route, state) => {
  const authService: AuthService = inject(AuthService);

  if (authService.userIsEmpleado()){
    return true;
  }
  authService.toHome();
  return false;
};
