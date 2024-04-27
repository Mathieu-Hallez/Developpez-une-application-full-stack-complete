import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private _isLogged : boolean = false;
  public get isLogged() : boolean {
    return this._isLogged
  }
  
  private _token : string= '';
  public get token() : string {
    return this._token
  }

  private $isLoggedSubject : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.isLogged);

  constructor() { }

  public $isLogged(): Observable<boolean> {
    return this.$isLoggedSubject.asObservable();
  }

  public login(token : string) {
    this._isLogged = true;
    this._token = token;
    this.next();
  }

  public logout() {
    this._isLogged = false;
    this._token = '';
    this.next();
  }

  private next(): void {
    this.$isLoggedSubject.next(this.isLogged);
  }
}
