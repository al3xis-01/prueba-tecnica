import { Component, OnInit } from '@angular/core';
import {ReactiveFormsModule, UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import Swal from 'sweetalert2'
import {AuthService} from "../../../../core/service/auth.service";
import {Credentials} from "../../../../core/interface/credentials";

@Component({
  selector: 'app-login-view',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login-view.component.html',
  styleUrl: './login-view.component.css'
})
export class LoginViewComponent implements OnInit{

  constructor(private fb: UntypedFormBuilder, private authService: AuthService) {
  }
  email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

  public formLogin: UntypedFormGroup =  this.fb.group({
    email: ["", [Validators.required, Validators.pattern(this.email_regex)]],
    password: ["", [Validators.required]]
  });

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.formLogin.invalid){
      Swal.fire({
        title: "Ingrese credenciales válidas",
        text: "",
        timer: 3000,
        icon: "error",
        toast: true,
        position: "top-right",
        showConfirmButton: false
      });
      return;
    }

    const email = this.formLogin.get("email")?.value;
    const password = btoa(this.formLogin.get("password")?.value);

    const credentials: Credentials = {
      email,
      password
    };

    Swal.showLoading();

    this.authService.login(credentials).subscribe(value => {
      Swal.close();
      console.log(value);
      this.authService.saveCredentials(value);
      this.authService.toHome();

    },(error) => {
      Swal.close();
      Swal.fire({
        title: "Credenciales inválidas",
        text: "",
        timer: 3000,
        icon: "error",
        toast: true,
        position: "top-right",
        showConfirmButton: false
      });
    });

  }
}
