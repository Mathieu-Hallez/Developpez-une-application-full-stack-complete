import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { PostDto } from 'src/app/interfaces/responses/PostDto';

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.scss']
})
export class PostCardComponent {

  @Input() post! : PostDto;

  constructor(
    private router : Router
  ) {}

  goToDetail(): void {
    this.router.navigateByUrl(`/session/posts/${this.post.id}`);
  }

}
