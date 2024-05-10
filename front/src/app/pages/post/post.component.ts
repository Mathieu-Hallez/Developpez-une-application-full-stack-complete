import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject, takeUntil, tap } from 'rxjs';
import { PostDto } from 'src/app/interfaces/responses/PostDto';
import { PostsApiService } from 'src/app/services/api/post/posts-api.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit, OnDestroy {

  private destroy$ : Subject<boolean> = new Subject<boolean>();

  post! : PostDto;
  
  constructor(
    private route : ActivatedRoute,
    private router : Router,
    private location : Location,
    private postsApiService : PostsApiService
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.params['id'];

    this.postsApiService.get(id).pipe(
      takeUntil(this.destroy$),
      tap(post => console.log("Post: " + JSON.stringify(post)))
    ).subscribe({
      next: post => this.post = post,
      error: err => {
        console.error(err?.error?.message);
        this.router.navigateByUrl('/404');
      }
    })
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  goBack(): void {
    this.location.back();
  }

}
