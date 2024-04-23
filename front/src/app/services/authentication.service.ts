import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterRequest } from '../interfaces/RegisterRequest';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private pathService : string = '/api/auth';

  constructor(private httpClient : HttpClient) { }

  public register(registerRequest : RegisterRequest): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/register`, registerRequest);
  }
}
