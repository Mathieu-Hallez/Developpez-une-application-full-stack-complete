import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-mdd-form-input',
  templateUrl: './mdd-form-input.component.html',
  styleUrls: ['./mdd-form-input.component.scss']
})
export class MddFormInputComponent implements OnInit {

  @Input()
  label! : string;

  @Input()
  myformControlName! : string;

  @Input()
  type : 'text' | 'password' = 'text';

  @Input()
  myFormGroup! : FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

}
