import { Location } from '@angular/common';
import { Component, OnDestroy } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { RegisterRequest } from 'src/app/interfaces/requests/RegisterRequest';
import { AuthenticationService } from 'src/app/services/api/authentification/authentication.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnDestroy {

  private destroy$ : Subject<boolean> = new Subject<boolean>();

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

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  onSubmitForm(): void {
    const registerRequest = this.signForm.value as RegisterRequest;
    this.authService.register(registerRequest)
      .pipe(
        takeUntil(this.destroy$)
      )
      .subscribe({
        next: _ => this.router.navigateByUrl('/sign-in'),
        error: error => this.errorMessage = error.error.message
      });
  }

  goBack(): void {
    this.location.back();
  }

}
