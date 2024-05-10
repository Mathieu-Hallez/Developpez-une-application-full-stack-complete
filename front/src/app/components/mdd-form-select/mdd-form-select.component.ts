import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MddSelectOption } from 'src/app/pipes/format-options.pipe';

@Component({
  selector: 'app-mdd-form-select',
  templateUrl: './mdd-form-select.component.html',
  styleUrls: ['./mdd-form-select.component.scss']
})
export class MddFormSelectComponent {
  @Input() options: MddSelectOption[] = [];

  @Input() myFormControlName!: string;

  @Input() myFormGroup!: FormGroup;

  @Input() placeholder: string = "";
}
