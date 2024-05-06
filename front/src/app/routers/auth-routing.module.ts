import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SignInComponent } from "../pages/sign-in/sign-in.component";
import { SignUpComponent } from "../pages/sign-up/sign-up.component";
import { HomeComponent } from "../pages/home/home.component";

const routes : Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'sign-up', component: SignUpComponent },
    { path: 'sign-in', component: SignInComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AuthRoutingModule {}