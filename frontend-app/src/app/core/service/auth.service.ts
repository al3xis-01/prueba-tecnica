import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AuthResponse} from "../interface/auth-response";
import {Credentials} from "../interface/credentials";
import {Router} from "@angular/router";
import {AppService} from "./app.service";
import {API_URL} from "../../../environments/environment.development";
import {User} from "../interface/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly tokenNameStorage: string;
  private readonly userNameStorage: string;

  constructor(
    private http: HttpClient,
    private route: Router,
    private appService: AppService
  ) {
    this.tokenNameStorage = this.appService.encode("token");
    this.userNameStorage = this.appService.encode("user");
  }

  login(credentials: Credentials): Observable<AuthResponse>{
    const url =  API_URL + '/Authentication/Login';
    return this.http.post<AuthResponse>(url, credentials);
  }

  saveCredentials(authResponse: AuthResponse): void{
    localStorage.setItem(this.tokenNameStorage, this.appService.encode(authResponse.token));
    localStorage.setItem(this.userNameStorage, this.appService.encode(JSON.stringify(authResponse.user)));
  }

  isLogged(): boolean {

    const token = localStorage.getItem(this.tokenNameStorage);
    const user = localStorage.getItem(this.userNameStorage);

    if (user && token){

      return true;
    }

    return false;
  }

  toLogin(): void{
   this.route.navigate(['Login']);
  }

  toHome(): void{
    this.route.navigate([""]);
  }


  token(): string{
    if (this.isLogged()){
      const token = this.appService.decode(localStorage.getItem(this.tokenNameStorage) ?? '');
      return token;
    }
    return "";
  }

  logout(): void {
    localStorage.clear();
  }

  user(): User | null{
    if (this.isLogged()){
      const token = this.appService.decode(localStorage.getItem(this.userNameStorage) ?? '');
      return JSON.parse(token);
    }
    return null;
  }

  userIsEmpleado(): boolean {
    const user = this.user();
    if (user?.id_user_role === 2){
      return true;
    }
    return false;
  }

  userIsAdmin(): boolean {
    const user = this.user();
    if (user?.id_user_role === 1){
      return true;
    }
    return false;
  }
}
