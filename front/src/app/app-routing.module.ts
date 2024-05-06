import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AuthGuard } from './guards/auth.guard';
import { UnAuthGuard } from './guards/un-auth.guard';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { 
    path: '',
    canActivate: [UnAuthGuard],
    loadChildren: () => import('./modules/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'session',
    canActivate: [AuthGuard],
    loadChildren: () => import('./modules/session.module').then(m => m.SessionModule)
  },
  { path: '404', component: NotFoundComponent},
  { path: '**', redirectTo: '404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,
    { enableTracing: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
