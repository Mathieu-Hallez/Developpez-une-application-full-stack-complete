import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreatePostDto } from 'src/app/interfaces/requests/CreatePostDto';
import { ApiResponseDto } from 'src/app/interfaces/responses/ApiResponseDto';

@Injectable({
  providedIn: 'root'
})
export class PostsApiService {
  
  private pathService : string = '/api/posts';

  constructor(private httpClient : HttpClient) { }

  create(post : CreatePostDto): Observable<ApiResponseDto> {
    return this.httpClient.post<ApiResponseDto>(`${this.pathService}/create`, post);
  }
}
