import { Component } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'front';

  constructor(
    public router : Router,
    private matIconRegister : MatIconRegistry,
    private domSaniziter : DomSanitizer
  ) {
    this.router.events.subscribe(() => console.log('Route:', router.url));
    this.matIconRegister.addSvgIcon(
      'user',
      this.domSaniziter.bypassSecurityTrustResourceUrl('../assets/icons/user.svg')
    )
  }
}
