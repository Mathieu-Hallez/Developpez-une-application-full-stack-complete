import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class BaseUrlInterceptor implements HttpInterceptor {

  constructor(
    @Inject('BASE_API_URL') private baseUrl : string
  ) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if(!request.url.includes('/assets/icons/'))
      return next.handle(request.clone({url: `${this.baseUrl}${request.url}`}));
    return next.handle(request.clone());
  }
}
