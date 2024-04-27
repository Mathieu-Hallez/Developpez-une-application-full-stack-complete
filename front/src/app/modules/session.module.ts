import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SessionRoutingModule } from '../routers/session-routing.module';
import { PostsComponent } from '../pages/posts/posts.component';



@NgModule({
  declarations: [PostsComponent],
  imports: [
    SessionRoutingModule,
    CommonModule
  ]
})
export class SessionModule { }
