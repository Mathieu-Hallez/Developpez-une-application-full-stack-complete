import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { PostsComponent } from "../pages/posts/posts.component";
import { TopicsComponent } from "../pages/topics/topics.component";
import { ProfileComponent } from "../pages/profile/profile.component";
import { CreatePostComponent } from "../pages/create-post/create-post.component";

const routes: Routes = [
    { path: 'posts', component: PostsComponent},
    { path: 'topics', component: TopicsComponent},
    { path: 'profile', component: ProfileComponent},
    { path: 'posts/create', component: CreatePostComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SessionRoutingModule {}