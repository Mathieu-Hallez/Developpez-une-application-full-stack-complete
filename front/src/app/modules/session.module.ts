import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SessionRoutingModule } from '../routers/session-routing.module';
import { PostsComponent } from '../pages/posts/posts.component';
import { PostCardComponent } from '../components/post-card/post-card.component';
import { MatCardModule } from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { TopicCardComponent } from '../components/topic-card/topic-card.component';
import { TopicsComponent } from '../pages/topics/topics.component';
import { ProfileComponent } from '../pages/profile/profile.component';
import { MatDividerModule } from '@angular/material/divider';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { ReactiveFormsModule } from "@angular/forms";
import { SharedModule } from './shared/shared.module';
import { CreatePostComponent } from '../pages/create-post/create-post.component';
import { PostComponent } from '../pages/post/post.component';
import {ScrollingModule} from '@angular/cdk/scrolling';

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
