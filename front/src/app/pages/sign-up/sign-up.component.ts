import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Location } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { RegisterRequest } from 'src/app/interfaces/requests/RegisterRequest';
import { AuthenticationService } from 'src/app/services/api/authentification/authentication.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit, OnDestroy {

  private register$! : Subscription;

  signForm : FormGroup = this.formBuilder.group({
    username: [
      '',
      [
        Validators.required
      ]
    ],
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
        Validators.required,
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/)
      ]
    ]
  });

  errorMessage : string | null = null;

  get usernameFormField(): AbstractControl<string> | null {
    return this.signForm.get('username');
  }


  get passwordFormField(): AbstractControl<string> | null {
    return this.signForm.get('password');
  }

  get emailFormField(): AbstractControl<string> | null {
    return this.signForm.get('email');
  }

  constructor(
    private formBuilder : FormBuilder,
    private location : Location,
    private authService : AuthenticationService,
    private router : Router
  ) { }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.register$?.unsubscribe();
  }

  onSubmitForm(): void {
    const registerRequest = this.signForm.value as RegisterRequest;
    console.log(registerRequest);
    console.log(this.signForm.errors);
    this.register$ = this.authService.register(registerRequest).subscribe({
      next: _ => this.router.navigateByUrl('/sign-in'),
      error: error => this.errorMessage = error.error.message
    })
  }

  goBack(): void {
    this.location.back();
  }

}
