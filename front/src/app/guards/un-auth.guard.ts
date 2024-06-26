import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { SessionService } from '../services/session.service';

@Injectable({
  providedIn: 'root'
})
export class UnAuthGuard implements CanActivate {
  
  constructor(
    private router : Router,
    private sessionService : SessionService
  ) {}

  canActivate(): boolean {
    if(this.sessionService.isLogged) {
      this.router.navigateByUrl('/session/posts');
      return false;
    }
    return true;
  }
  
}
