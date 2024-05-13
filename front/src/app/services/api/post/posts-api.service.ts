import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreatePostDto } from 'src/app/interfaces/requests/CreatePostDto';
import { ApiResponseDto } from 'src/app/interfaces/responses/ApiResponseDto';
import { CommentDto } from 'src/app/interfaces/responses/CommentDto';
import { PostDto } from 'src/app/interfaces/responses/PostDto';

@Injectable({
  providedIn: 'root'
})
export class PostsApiService {
  
  private pathService : string = '/api/posts';

  constructor(private httpClient : HttpClient) { }

  create(post : CreatePostDto): Observable<ApiResponseDto> {
    return this.httpClient.post<ApiResponseDto>(`${this.pathService}/create`, post);
  }

  get(id : number): Observable<PostDto> {
    return this.httpClient.get<PostDto>(`${this.pathService}/${id}`);
  }

  getComments(id : number): Observable<Array<CommentDto>> {
    return this.httpClient.get<Array<CommentDto>>(`${this.pathService}/${id}/comments`);
  }

  comment(id : number, content : string): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/${id}/comment`, {content: content});
  }
}
