import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterRequest } from '../../../interfaces/requests/RegisterRequest';
import { LoginRequest } from 'src/app/interfaces/requests/LoginRequest';
import { TokenDto } from 'src/app/interfaces/responses/TokenDto';
import { ApiResponseDto } from 'src/app/interfaces/responses/ApiResponseDto';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private pathService : string = '/api/auth';

  constructor(private httpClient : HttpClient) { }

  public register(registerRequest : RegisterRequest): Observable<ApiResponseDto> {
    return this.httpClient.post<ApiResponseDto>(`${this.pathService}/register`, registerRequest);
  }

  public login(loginRequest : LoginRequest): Observable<TokenDto> {
    return this.httpClient.post<TokenDto>(`${this.pathService}/login`, loginRequest);
  }
}
