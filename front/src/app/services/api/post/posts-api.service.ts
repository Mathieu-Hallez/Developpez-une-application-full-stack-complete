import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostsApiService {
  
  private pathService : string = '/api/posts';

  constructor(private httpClient : HttpClient) { }
}
