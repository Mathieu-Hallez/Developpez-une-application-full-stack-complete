import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MddFormInputComponent } from 'src/app/components/mdd-form-input/mdd-form-input.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [MddFormInputComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    MddFormInputComponent
  ]
})
export class SharedModule { }
