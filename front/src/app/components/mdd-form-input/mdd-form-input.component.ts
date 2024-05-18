import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-mdd-form-input',
  templateUrl: './mdd-form-input.component.html',
  styleUrls: ['./mdd-form-input.component.scss']
})
export class MddFormInputComponent {

  @Input()
  label! : string;

  @Input()
  myformControlName! : string;

  @Input()
  type : 'text' | 'password' = 'text';

  @Input()
  myFormGroup! : FormGroup;

  @Input() placeholder: string = "";

}
