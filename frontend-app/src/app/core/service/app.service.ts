import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor() { }

  encode(text: string): string{
    return btoa(text);
  }

  decode(encode: string): string{
    return atob(encode);
  }

}
