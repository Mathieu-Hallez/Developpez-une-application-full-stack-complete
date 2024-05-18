import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { CreatePostComponent } from "../pages/create-post/create-post.component";
import { PostComponent } from "../pages/post/post.component";
import { PostsComponent } from "../pages/posts/posts.component";
import { ProfileComponent } from "../pages/profile/profile.component";
import { TopicsComponent } from "../pages/topics/topics.component";

const routes: Routes = [
    { path: 'posts', component: PostsComponent},
    { path: 'topics', component: TopicsComponent},
    { path: 'profile', component: ProfileComponent},
    { path: 'posts/create', component: CreatePostComponent},
    { path: 'posts/:id', component: PostComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SessionRoutingModule {}