import { Component, HostListener, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { PostDto } from 'src/app/interfaces/responses/PostDto';
import { PostsApiService } from 'src/app/services/api/post/posts-api.service';
import { UsersApiService } from 'src/app/services/api/users/users-api.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss'],
})
export class PostsComponent implements OnInit {

  posts$ : Observable<PostDto[]> = this.usersApiService.allSubscriptionsPosts();

  decreasingOrder : boolean = false;
  
  screenWidth! : number;

  constructor(
    private usersApiService : UsersApiService,
    private sessionService : SessionService
  ) { }

  ngOnInit(): void {
    this.screenWidth = window.innerWidth;
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
