import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TopicDetailsDto } from 'src/app/interfaces/responses/TopicDetailsDto';
import { TopicsApiService } from 'src/app/services/api/topic/topics-api.service';

@Component({
  selector: 'app-topic-card',
  templateUrl: './topic-card.component.html',
  styleUrls: ['./topic-card.component.scss']
})
export class TopicCardComponent {
  @Input() topic! : TopicDetailsDto;
  @Input('unsubscribe-action') hasUnsubscribeBtn : boolean = false;

  @Output() unsubscribeSuccessfully : EventEmitter<void> = new EventEmitter();

  errorMessage : string | null = null;

  constructor(
    private topicApiService : TopicsApiService
  ) {}

  subscribe(): void {
    this.topicApiService.subscribe(this.topic.id).subscribe({
      next: topic => {
        this.topic = topic;
      },
      error: err => {
        this.errorMessage = err.error.message;
      }
    });
  }

  unsubscribe(): void {
    this.topicApiService.unsubscribe(this.topic.id).subscribe({
      next: _ => this.unsubscribeSuccessfully.emit(),
      error: err => {} //TODO Toast
    })
  }
}