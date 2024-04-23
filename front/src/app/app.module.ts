import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { SignUpComponent } from './pages/sign-up/sign-up.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { HeaderAppComponent } from './components/header-app/header-app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import { ReactiveFormsModule } from '@angular/forms';
import { MddFormInputComponent } from './components/mdd-form-input/mdd-form-input.component';
import { environment } from 'src/environments/environment';
import { BaseUrlInterceptor } from './interceptors/base-url.interceptor';

@NgModule({
  declarations: [AppComponent, HomeComponent, SignUpComponent, SignInComponent, HeaderAppComponent, MddFormInputComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    HttpClientModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BaseUrlInterceptor,
      multi: true
    },
    {
      provide: "BASE_API_URL", useValue: environment.baseUrl
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
