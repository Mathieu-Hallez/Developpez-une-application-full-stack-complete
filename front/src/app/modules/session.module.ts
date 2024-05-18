import { ScrollingModule } from '@angular/cdk/scrolling';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { PostCardComponent } from '../components/post-card/post-card.component';
import { TopicCardComponent } from '../components/topic-card/topic-card.component';
import { CreatePostComponent } from '../pages/create-post/create-post.component';
import { PostComponent } from '../pages/post/post.component';
import { PostsComponent } from '../pages/posts/posts.component';
import { ProfileComponent } from '../pages/profile/profile.component';
import { TopicsComponent } from '../pages/topics/topics.component';
import { SessionRoutingModule } from '../routers/session-routing.module';
import { SharedModule } from './shared/shared.module';

const MatModule = [
  MatCardModule,
  MatIconModule,
  MatButtonModule,
  MatDividerModule,
  MatSnackBarModule,
  ScrollingModule
];


@NgModule({
  declarations: [
    PostsComponent,
    PostCardComponent,
    TopicCardComponent,
    TopicsComponent,
    ProfileComponent,
    CreatePostComponent,
    PostComponent
  ],
  imports: [
    SessionRoutingModule,
    CommonModule,
    FlexLayoutModule,
    ...MatModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class SessionModule { }
