import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public isLogged : boolean = false;
  public token : string= '';

  private $isLoggedSubject : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.isLogged);

  constructor() { }

  public $isLogged(): Observable<boolean> {
    return this.$isLoggedSubject.asObservable();
  }

  public login(_token : string) {
    this.isLogged = true;
    this.token = _token;
    this.next();
  }

  public logout() {
    this.isLogged = false;
    this.token = '';
    this.next();
  }

  private next(): void {
    this.$isLoggedSubject.next(this.isLogged);
  }
}
