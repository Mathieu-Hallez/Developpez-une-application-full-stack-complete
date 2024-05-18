import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AuthGuard } from './guards/auth.guard';
import { UnAuthGuard } from './guards/un-auth.guard';

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
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
