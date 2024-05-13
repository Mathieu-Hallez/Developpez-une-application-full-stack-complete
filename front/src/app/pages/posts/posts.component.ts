import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as dayjs from 'dayjs';
import { Subject, map, takeUntil } from 'rxjs';
import { PostDto } from 'src/app/interfaces/responses/PostDto';
import { UsersApiService } from 'src/app/services/api/users/users-api.service';

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
    private router : Router
  ) { }

  ngOnInit(): void {
    this.usersApiService.allSubscriptionsPosts().pipe(
      takeUntil(this.destroy$),
      map(posts => posts.sort(this.comparePost))
    ).subscribe({
      next: posts => {
        this.posts = posts;
        this.descendingOrder = false;
      }
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  createAPost(): void {
    this.router.navigateByUrl('/session/posts/create');
  }

  @HostListener('window:resize', ['$event'])  
  onResize() {  
    this.screenWidth = window.innerWidth;
  }

  switchOrderBy(): void {
    this.descendingOrder = !this.descendingOrder;
    this.posts = this.posts.sort(this.comparePost);
  }

  comparePost = (a : PostDto, b : PostDto): number => {
    const dateA = dayjs(a.created_at);
    const dateB = dayjs(b.created_at);
    if(this.descendingOrder) {
      return dateA.diff(dateB);
    } else {
      return dateB.diff(dateA);
    }
  }
}
