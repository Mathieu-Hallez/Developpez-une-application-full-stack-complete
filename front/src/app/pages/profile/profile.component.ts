import { Component, OnDestroy, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject, takeUntil, tap } from 'rxjs';
import { TopicDetailsDto } from 'src/app/interfaces/responses/TopicDetailsDto';
import { UpdateUserDto } from 'src/app/interfaces/responses/UpdateUserDto';
import { UsersApiService } from 'src/app/services/api/users/users-api.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {
  private destroy$: Subject<boolean> = new Subject<boolean>();

  topics$! : Observable<TopicDetailsDto[]>;

  profileForm : FormGroup = this.formBuilder.group({
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
    ]
  });

  errorMessage : string | null = null;

  get usernameFormField(): AbstractControl<string> | null {
    return this.profileForm.get('username');
  }

  get emailFormField(): AbstractControl<string> | null {
    return this.profileForm.get('email');
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
        this.profileForm.setValue({username: user.username, email: user.email});
      }
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

  fetchTopics() {
    this.topics$ = this.userApiService.subscriptions().pipe(
      takeUntil(this.destroy$)
    );
  }

  logout(): void {
    this.sessionService.logout();
  }

  onSubmitForm(): void {
    const userUpdateRequest = this.profileForm.value as UpdateUserDto;
    this.userApiService.updateUser(userUpdateRequest).pipe(
      takeUntil(this.destroy$)
    ).subscribe({
      next: userUpdated => {
        this.profileForm.setValue({username: userUpdated.username, email: userUpdated.email});
      },
      error: error => this.errorMessage = error.error.message
    })
  }
}
