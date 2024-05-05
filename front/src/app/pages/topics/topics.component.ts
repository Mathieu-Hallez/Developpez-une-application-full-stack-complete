import { Component, OnDestroy } from '@angular/core';
import { Observable, Subject, takeUntil } from 'rxjs';
import { TopicDetailsDto } from 'src/app/interfaces/responses/TopicDetailsDto';
import { TopicsApiService } from 'src/app/services/api/topic/topics-api.service';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnDestroy {

  
  private destroy$: Subject<boolean> = new Subject<boolean>();

  topics$ : Observable<TopicDetailsDto[]> = this.topicApiService.all().pipe(
    takeUntil(this.destroy$)
  );

  constructor(
    private topicApiService : TopicsApiService
  ) {}

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }
}
