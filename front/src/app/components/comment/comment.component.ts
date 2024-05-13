import { Component, Input } from '@angular/core';
import { CommentDto } from 'src/app/interfaces/responses/CommentDto';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent {

  @Input() comment! : CommentDto
}
