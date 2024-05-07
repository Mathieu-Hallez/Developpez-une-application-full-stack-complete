import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import * as dayjs from 'dayjs';
import { Observable, Subject, takeUntil } from 'rxjs';
import { PostDto } from 'src/app/interfaces/responses/PostDto';
import { UsersApiService } from 'src/app/services/api/users/users-api.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss'],
})
export class PostsComponent implements OnDestroy, OnInit {

  private destroy$ : Subject<boolean> = new Subject<boolean>();

  posts : PostDto[] = [];

  descendingOrder : boolean = false;

  screenWidth : number = window.innerWidth;

  constructor(
    private usersApiService : UsersApiService,
    private sessionService : SessionService
  ) { }

  ngOnInit(): void {
    this.usersApiService.allSubscriptionsPosts().pipe(takeUntil(this.destroy$)).subscribe({
      next: posts => this.posts = posts
    });
  }

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

  switchOrderBy(): void {
    this.descendingOrder = !this.descendingOrder;
    this.posts = this.posts.sort(this.comparePost)
  }

  comparePost = (a : PostDto, b : PostDto): number => {
    const dateA = dayjs(a.created_at);
    const dateB = dayjs(b.created_at);
    if(this.descendingOrder) {
      return dateB.diff(dateA);
    } else {
      return dateA.diff(dateB);
    }
  }
}
