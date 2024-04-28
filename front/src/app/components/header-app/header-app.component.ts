import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout'; 
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-header-app',
  templateUrl: './header-app.component.html',
  styleUrls: ['./header-app.component.scss']
})
export class HeaderAppComponent implements OnInit {

  public isSmall : boolean = false;

  constructor(
    private breakpointObserver : BreakpointObserver,
    public router : Router,
    public sessionService : SessionService
  ) { }

  ngOnInit(): void {
    this.breakpointObserver.observe([
      Breakpoints.XSmall,
      Breakpoints.HandsetPortrait
    ]).subscribe(result => {
        this.isSmall = result.matches;
    });
  }
}
