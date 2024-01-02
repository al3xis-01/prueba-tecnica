import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EmpleadoViewComponent} from "./components/empleado-view/empleado-view.component";

const routes: Routes = [
  {
    path: "",
    component: EmpleadoViewComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmpleadoRoutingModule { }
