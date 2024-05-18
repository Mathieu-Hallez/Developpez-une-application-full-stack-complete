import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from 'src/app/interfaces/requests/LoginRequest';
import { ApiResponseDto } from 'src/app/interfaces/responses/ApiResponseDto';
import { TokenDto } from 'src/app/interfaces/responses/TokenDto';
import { RegisterRequest } from '../../../interfaces/requests/RegisterRequest';

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
