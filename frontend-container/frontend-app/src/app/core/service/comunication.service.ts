import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ComunicationService {

  constructor() { }

  private _sidebarState = new Subject<boolean>();
  sidebarState$ = this._sidebarState.asObservable();

  toggleSidebar(state: boolean) {
    this._sidebarState.next(state);
  }
}
