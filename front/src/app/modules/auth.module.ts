import { NgModule } from "@angular/core";
import { AuthRoutingModule } from "../routers/auth-routing.module";
import { CommonModule } from "@angular/common";
import { SignInComponent } from "../pages/sign-in/sign-in.component";
import { SignUpComponent } from "../pages/sign-up/sign-up.component";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatListModule } from "@angular/material/list";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatToolbarModule } from "@angular/material/toolbar";
import { ReactiveFormsModule } from "@angular/forms";
import { HomeComponent } from "../pages/home/home.component";
import { FlexLayoutModule } from '@angular/flex-layout';
import { SharedModule } from "./shared/shared.module";

const MatModule = [
  MatButtonModule,
  MatIconModule,
  MatToolbarModule,
  MatSidenavModule,
  MatListModule,
]

@NgModule({
  declarations: [
    HomeComponent,
    SignInComponent,
    SignUpComponent
  ],
  imports: [
    AuthRoutingModule,
    CommonModule,
    FlexLayoutModule,
    ...MatModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class AuthModule { }