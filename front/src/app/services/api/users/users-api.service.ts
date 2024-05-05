import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PostDto } from 'src/app/interfaces/responses/PostDto';
import { TopicDetailsDto } from 'src/app/interfaces/responses/TopicDetailsDto';
import { TopicDto } from 'src/app/interfaces/responses/TopicDto';

@Injectable({
  providedIn: 'root'
})
export class UsersApiService {
  
  private pathService : string = '/api/users';

  constructor(private httpClient : HttpClient) { }

  public allSubscriptionsPosts(): Observable<Array<PostDto>> {
    return this.httpClient.get<Array<PostDto>>(`${this.pathService}/subscriptions/posts`);
  }

  public subscriptions(): Observable<Array<TopicDetailsDto>> {
    return this.httpClient.get<Array<TopicDetailsDto>>(`${this.pathService}/subscriptions`);
  }
}
