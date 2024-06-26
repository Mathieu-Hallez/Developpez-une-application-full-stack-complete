import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SessionService } from '../services/session.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private sessionService : SessionService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if(this.sessionService.isLogged) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.sessionService.token}`
        }
      });
    }
    return next.handle(request);
  }
}
