import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { PostsComponent } from "../pages/posts/posts.component";

const routes: Routes = [
    { path: 'posts', component: PostsComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SessionRoutingModule {}