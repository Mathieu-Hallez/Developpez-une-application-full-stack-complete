import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { PostsComponent } from "../pages/posts/posts.component";
import { TopicsComponent } from "../pages/topics/topics.component";

const routes: Routes = [
    { path: 'posts', component: PostsComponent},
    { path: 'topics', component: TopicsComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SessionRoutingModule {}