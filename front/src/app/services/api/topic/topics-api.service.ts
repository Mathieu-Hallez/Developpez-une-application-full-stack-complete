import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TopicDetailsDto } from 'src/app/interfaces/responses/TopicDetailsDto';

@Injectable({
  providedIn: 'root'
})
export class TopicsApiService {

  private pathService : string = '/api/topics';

  constructor(
    private httpClient : HttpClient
  ) { }

  public all(): Observable<Array<TopicDetailsDto>> {
    return this.httpClient.get<Array<TopicDetailsDto>>(this.pathService + '/');
  }

  public subscribe(id : number): Observable<TopicDetailsDto> {
    return this.httpClient.put<TopicDetailsDto>(`${this.pathService}/${id}/subscribe`, {});
  }

  public unsubscribe(id : number): Observable<void> {
    return this.httpClient.put<void>(`${this.pathService}/${id}/unsubscribe`, {});
  }
}
