import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MddFormInputComponent } from 'src/app/components/mdd-form-input/mdd-form-input.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MddFormSelectComponent } from 'src/app/components/mdd-form-select/mdd-form-select.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { FormatOptionsPipe } from 'src/app/pipes/format-options.pipe';
import { MddFormTextareaComponent } from 'src/app/components/mdd-form-textarea/mdd-form-textarea.component';
import { CommentComponent } from 'src/app/components/comment/comment.component';
import { FlexLayoutModule } from '@angular/flex-layout';

const MatModule = [
  MatFormFieldModule,
  MatSelectModule,
  MatInputModule
]

@NgModule({
  declarations: [
    MddFormInputComponent,
    MddFormSelectComponent,
    FormatOptionsPipe,
    MddFormTextareaComponent,
    CommentComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ...MatModule,
    FlexLayoutModule
  ],
  exports: [
    MddFormInputComponent,
    MddFormSelectComponent,
    FormatOptionsPipe,
    MddFormTextareaComponent,
    CommentComponent
  ]
})
export class SharedModule { }
