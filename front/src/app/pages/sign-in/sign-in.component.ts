import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { LoginRequest } from 'src/app/interfaces/requests/LoginRequest';
import { AuthenticationService } from 'src/app/services/api/authentification/authentication.service';
import { Location } from '@angular/common';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit, OnDestroy {
  
  private destroy$: Subject<boolean> =new Subject<boolean>();

  signForm : FormGroup = this.formBuilder.group({
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ],
    password: [
      '',
      [
        Validators.required
      ]
    ]
  });

  errorMessage : string | null = null;

  constructor(
    private formBuilder : FormBuilder,
    private authService : AuthenticationService,
    private sessionService : SessionService,
    private router : Router,
    private location : Location
  ) { }

  ngOnInit(): void {
    this.signForm.valueChanges.pipe(
      takeUntil(this.destroy$)
    ).subscribe(_ => this.errorMessage = null);
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  onSubmitForm(): void {
    const loginRequest = this.signForm.value as LoginRequest;
    this.authService.login(loginRequest).pipe(
      takeUntil(this.destroy$)
    ).subscribe({
      next: data => {
        this.sessionService.login(data.token);
        this.router.navigateByUrl('/session/posts');
      },
      error: error => this.errorMessage = error.error.message
    })
  }

  goBack(): void {
    this.location.back();
  }

}
