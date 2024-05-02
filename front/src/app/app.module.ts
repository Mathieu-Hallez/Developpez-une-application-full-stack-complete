import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderAppComponent } from './components/header-app/header-app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import { ReactiveFormsModule } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { BaseUrlInterceptor } from './interceptors/base-url.interceptor';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AuthModule } from './modules/auth.module';
import { SessionModule } from './modules/session.module';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { ExpirySessionInterceptor } from './interceptors/expiry-session.interceptor';
import { PostCardComponent } from './components/post-card/post-card.component';
import {MatCardModule} from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RouterModule } from '@angular/router';

const MatModule = [
  MatButtonModule,
  MatIconModule,
  MatToolbarModule,
  MatSidenavModule,
  MatListModule,
]

@NgModule({
  declarations: [AppComponent, HeaderAppComponent, NotFoundComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    ...MatModule,
    AuthModule,
    SessionModule,
    FlexLayoutModule,
    RouterModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ExpirySessionInterceptor,
      multi: true
    },
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
