import { Location } from '@angular/common';
import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Observable, Subject, takeUntil } from 'rxjs';
import { TopicDetailsDto } from 'src/app/interfaces/responses/TopicDetailsDto';
import { MddSelectOption } from 'src/app/pipes/format-options.pipe';
import { PostsApiService } from 'src/app/services/api/post/posts-api.service';
import { TopicsApiService } from 'src/app/services/api/topic/topics-api.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnDestroy {

  private destroy$ : Subject<boolean> = new Subject<boolean>();

  errorMessage : string | null = null;

  postForm = this.formBuilder.group({
    topic: [
      null,
      [
        Validators.required
      ]
    ],
    title: [
      '',
      [
        Validators.required
      ]
    ],
    content: [
      '',
      [
        Validators.required
      ]
    ]
  });

  topics$ : Observable<TopicDetailsDto[]> = this.topicsApiService.all();
  
  formatTopicOption = (value : TopicDetailsDto) : MddSelectOption => ({title: value.title, value: String(value.id)});

  constructor(
    private formBuilder : FormBuilder,
    private topicsApiService : TopicsApiService,
    private postsApiService : PostsApiService,
    private location : Location,
  ) {}

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  onSubmitForm(): void {
    this.postsApiService.create({
      topic_id: Number(this.postForm.value.topic) ?? 0,
      title: this.postForm.value.title ?? '',
      content: this.postForm.value.content ?? ''
    }).pipe(takeUntil(this.destroy$)).subscribe({
      next: data => {
        console.log(data.message);
        this.location.back();
      },
      error: err => this.errorMessage = err.error.message
    });
  }

  goBack(): void {
    this.location.back();
  }

}
