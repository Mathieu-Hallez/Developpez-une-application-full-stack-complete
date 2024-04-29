import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public get isLogged() : boolean {
    return this.localStorageService.getItem('token') != null;
  }
  
  public get token() : string | null {
    return this.localStorageService.getItem('token');
  }

  private $isLoggedSubject : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.isLogged);

  constructor(
    private localStorageService : LocalStorageService
  ) { }

  public $isLogged(): Observable<boolean> {
    return this.$isLoggedSubject.asObservable();
  }

  public login(token : string) {
    this.localStorageService.setItem('token', token);
    this.next();
  }

  public logout() {
    this.localStorageService.removeItem('token');
    this.next();
  }

  private next(): void {
    this.$isLoggedSubject.next(this.isLogged);
  }
}
