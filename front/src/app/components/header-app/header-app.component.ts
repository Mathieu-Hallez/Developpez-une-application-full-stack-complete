import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-header-app',
  templateUrl: './header-app.component.html',
  styleUrls: ['./header-app.component.scss']
})
export class HeaderAppComponent implements OnInit {

  private destroy$ : Subject<boolean> = new Subject<boolean>();

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
    ])
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(result => {
        this.isSmall = result.matches;
    });
  }
}
