import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject, map, takeUntil, tap } from 'rxjs';
import { PostDto } from 'src/app/interfaces/responses/PostDto';
import { PostsApiService } from 'src/app/services/api/post/posts-api.service';
import { Location } from '@angular/common';
import { CommentDto } from 'src/app/interfaces/responses/CommentDto';
import * as dayjs from 'dayjs';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit, OnDestroy {

  private destroy$ : Subject<boolean> = new Subject<boolean>();

  private compareComment = (a : CommentDto, b : CommentDto): number => {
    const dateA = dayjs(a.created_at);
    const dateB = dayjs(b.created_at);
    return dateB.diff(dateA);
  }

  post! : PostDto;
  comments! : Array<CommentDto>;
  commentForm : FormGroup = this.formBuilder.group({
    content: [
      '',
      [
        Validators.required,
        Validators.maxLength(255)
      ]
    ]
  });
  xSmallBreakPoint : boolean = false;

  get contentField(): AbstractControl<string> | null {
    return this.commentForm.get('content');
  } 
  
  constructor(
    private route : ActivatedRoute,
    private router : Router,
    private location : Location,
    private postsApiService : PostsApiService,
    private formBuilder : FormBuilder,
    private responsive: BreakpointObserver,
    private matSnackBar : MatSnackBar
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.params['id'];

    this.postsApiService.get(id).pipe(
      takeUntil(this.destroy$),
      tap(post => console.log("Post: " + JSON.stringify(post)))
    ).subscribe({
      next: post => {
        this.post = post;
        this.fetchComments();
      },
      error: err => {
        console.error(err?.error?.message);
        this.router.navigateByUrl('/404');
      }
    });

    this.responsive.observe(Breakpoints.XSmall)
      .subscribe(result => {

        if (result.matches) {
          this.xSmallBreakPoint = true;
        } else {
          this.xSmallBreakPoint = false;
        }

  });
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  goBack(): void {
    this.location.back();
  }

  fetchComments(): void {
    this.postsApiService.getComments(this.post.id).pipe(
      takeUntil(this.destroy$),
      map(comments => comments.sort(this.compareComment)),
      tap(comments => console.log("Comments of post: " + JSON.stringify(comments)))
    ).subscribe({
      next: comments => this.comments = comments,
      error: _ => {
        this.matSnackBar.open(
          "Une erreur s'est produite.",
          "Fermer",
          {
            horizontalPosition: 'center',
            verticalPosition: 'bottom'
          }
        );
      }
    });
  }

  onSendComment(): void {
    this.postsApiService.comment(this.post.id, this.commentForm.value['content']).pipe(
      takeUntil(this.destroy$)
    ).subscribe({
      next: _ => {
        this.commentForm.reset();
        this.fetchComments();
      },
      error: _ => {
        this.matSnackBar.open(
          "Une erreur s'est produite.",
          "Fermer",
          {
            horizontalPosition: 'center',
            verticalPosition: 'bottom'
          }
        );
      }
    })
  }
}
