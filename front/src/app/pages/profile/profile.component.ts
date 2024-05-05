import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subject, takeUntil, tap } from 'rxjs';
import { TopicDetailsDto } from 'src/app/interfaces/responses/TopicDetailsDto';
import { UsersApiService } from 'src/app/services/api/users/users-api.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {
  private destroy$: Subject<boolean> = new Subject<boolean>();

  topics$! : Observable<TopicDetailsDto[]>;

  constructor(
    private userApiService : UsersApiService,
    private sessionService : SessionService
  ) {}

  ngOnInit(): void {
    this.fetchTopics()
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  fetchTopics() {
    this.topics$ = this.userApiService.subscriptions().pipe(
      takeUntil(this.destroy$),
      tap((value) => console.log("Topics: " + JSON.stringify(value)))
    );
  }

  logout(): void {
    this.sessionService.logout();
  }
}
