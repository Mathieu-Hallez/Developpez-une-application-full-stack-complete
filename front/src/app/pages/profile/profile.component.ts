import { Component, OnDestroy, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject, takeUntil, tap } from 'rxjs';
import { TopicDetailsDto } from 'src/app/interfaces/responses/TopicDetailsDto';
import { UsersApiService } from 'src/app/services/api/users/users-api.service';
import { SessionService } from 'src/app/services/session.service';
import CustomValidators from 'src/app/utils/CustomValidators';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {
  private destroy$: Subject<boolean> = new Subject<boolean>();

  topics$! : Observable<TopicDetailsDto[]>;

  profileForm : FormGroup = this.formBuilder.group(
    {
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
          Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/)
        ]
      ],
      confirm: [
        '',
        []
      ]
    },
    {
      validators: [
        CustomValidators.match('password', 'confirm')
      ]
    }
  );

  errorMessage : string | null = null;

  get usernameFormField(): AbstractControl<string> | null {
    return this.profileForm.get('username');
  }

  get emailFormField(): AbstractControl<string> | null {
    return this.profileForm.get('email');
  }

  get passwordFormField(): AbstractControl<string> | null {
    return this.profileForm.get('password');
  }

  get confirmFormField(): AbstractControl<string> | null {
    return this.profileForm.get('confirm');
  }

  constructor(
    private userApiService : UsersApiService,
    private sessionService : SessionService,
    private formBuilder : FormBuilder
  ) {}

  ngOnInit(): void {
    this.fetchTopics();

    this.userApiService.me().pipe(
      takeUntil(this.destroy$),
      tap(user => console.log("User: " + JSON.stringify(user)))
    ).subscribe({
      next: user => {
        this.profileForm.setValue({username: user.username, email: user.email, password: '', confirm: ''});
      }
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  fetchTopics(): void {
    this.topics$ = this.userApiService.subscriptions().pipe(
      takeUntil(this.destroy$)
    );
  }

  logout(): void {
    this.sessionService.logout();
  }

  onSubmitForm(): void {
    let userUpdateRequest : Record<string,any> = {};
    if(this.profileForm.value['username']) {
      userUpdateRequest['username'] = this.profileForm.value['username']
    }
    
    if(this.profileForm.value['email']) {
      userUpdateRequest['email'] = this.profileForm.value['email']
    }
    
    if(this.profileForm.value['password']) {
      userUpdateRequest['password'] = this.profileForm.value['password']
    }
    console.log("User Update: " + JSON.stringify(userUpdateRequest));
    this.userApiService.updateUser(userUpdateRequest).pipe(
      takeUntil(this.destroy$)
    ).subscribe({
      next: userUpdated => {
        this.profileForm.setValue({username: userUpdated.username, email: userUpdated.email, password: '', confirm: ''});
        this.sessionService.logout();
      },
      error: error => this.errorMessage = error.error.message
    })
  }
}
