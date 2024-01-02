import { Routes } from '@angular/router';
import {authGuard} from "./core/guard/auth.guard";
import {authNotGuard} from "./core/guard/auth-not.guard";
import {MainComponent} from "./core/components/main/main.component";
import {adminGuard} from "./core/guard/admin.guard";
import {empleadoGuard} from "./core/guard/empleado.guard";

export const routes: Routes = [
  {
    path: "",
    canActivate: [authGuard],
    component: MainComponent,
    children: [
      {
        path: "",
        loadChildren: () => import('./module/home/home.module').then(value => value.HomeModule)
      },
      {
        path: "admin",
        loadChildren: () => import("./module/admin/admin.module").then(value => value.AdminModule),
        canActivate: [adminGuard]
      },
      {
        path: "empleado",
        loadChildren: () => import("./module/empleado/empleado.module").then(value => value.EmpleadoModule),
        canActivate: [empleadoGuard]
      },
    ],

  },
  {
    path: "Login",
    canActivate: [authNotGuard],
    loadChildren: () => import('./module/authentication/authentication.module').then(value => value.AuthenticationModule)
  },
];
