import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-mdd-form-textarea',
  templateUrl: './mdd-form-textarea.component.html',
  styleUrls: ['./mdd-form-textarea.component.scss']
})
export class MddFormTextareaComponent {

  @Input() myFormControlName!: string;

  @Input() myFormGroup!: FormGroup;

  @Input() placeholder: string = "";
}
