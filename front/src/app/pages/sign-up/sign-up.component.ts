import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signForm! : FormGroup;
  isSmall : boolean = false;

  constructor(
    private formBuilder : FormBuilder,
    private location : Location,
    private breakpointObserver : BreakpointObserver,
  ) { }

  ngOnInit(): void {
    this.signForm = this.formBuilder.group({
      username: [null],
      email: [null],
      password: [null]
    });

    
    this.breakpointObserver.observe([
      Breakpoints.XSmall,
      Breakpoints.HandsetPortrait
    ]).subscribe(result => {
        this.isSmall = result.matches;
    });
  }

  onSubmitForm() {
    console.log(this.signForm.value);
  }

  goBack() {
    this.location.back();
  }

}
