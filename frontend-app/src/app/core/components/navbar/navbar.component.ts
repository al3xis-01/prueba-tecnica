import { Component } from '@angular/core';
import {NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {ComunicationService} from "../../service/comunication.service";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    NgIf,
    RouterLink
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  sidebarOpen = true;
  constructor(private communicationService: ComunicationService, private authService: AuthService) { }

  ngOnInit() {
    this.communicationService.sidebarState$.subscribe(state => {
      this.sidebarOpen = state;
    });
  }

  logout(): void {
    this.authService.logout();
    this.authService.toLogin();
  }

  isAdmin(): boolean {
    return this.authService.userIsAdmin();
  }
}
