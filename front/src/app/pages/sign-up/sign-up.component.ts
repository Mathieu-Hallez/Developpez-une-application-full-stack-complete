import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signForm! : FormGroup;

  constructor(private formBuilder : FormBuilder) { }

  ngOnInit(): void {
    this.signForm = this.formBuilder.group({
      username: [null],
      email: [null],
      password: [null]
    })
  }

  onSubmitForm() {
    console.log(this.signForm.value);
  }

}
