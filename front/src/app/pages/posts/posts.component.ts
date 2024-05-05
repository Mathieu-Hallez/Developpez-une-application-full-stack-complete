import { Component, HostListener, OnDestroy } from '@angular/core';
import { Observable, Subject, takeUntil } from 'rxjs';
import { PostDto } from 'src/app/interfaces/responses/PostDto';
import { UsersApiService } from 'src/app/services/api/users/users-api.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss'],
})
export class PostsComponent implements OnDestroy {

  private destroy$ : Subject<boolean> = new Subject<boolean>();

  posts$ : Observable<PostDto[]> = this.usersApiService.allSubscriptionsPosts().pipe(takeUntil(this.destroy$));

  decreasingOrder : boolean = false;

  screenWidth : number = window.innerWidth;

  constructor(
    private usersApiService : UsersApiService,
    private sessionService : SessionService
  ) { }

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  createAPost(): void {
    this.sessionService.logout();
  }

  @HostListener('window:resize', ['$event'])  
  onResize() {  
    this.screenWidth = window.innerWidth;
    console.log(this.screenWidth)
  }  

}
