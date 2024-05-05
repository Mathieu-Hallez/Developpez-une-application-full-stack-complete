import { Component, HostListener, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { TopicDetailsDto } from 'src/app/interfaces/responses/TopicDetailsDto';
import { TopicsApiService } from 'src/app/services/api/topic/topics-api.service';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {

  topics$! : Observable<TopicDetailsDto[]>;

  constructor(
    private topicApiService : TopicsApiService
  ) {}

  ngOnInit(): void {
    this.topics$ = this.topicApiService.all();
  }
}
