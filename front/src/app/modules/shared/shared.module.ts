import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { CommentComponent } from 'src/app/components/comment/comment.component';
import { MddFormInputComponent } from 'src/app/components/mdd-form-input/mdd-form-input.component';
import { MddFormSelectComponent } from 'src/app/components/mdd-form-select/mdd-form-select.component';
import { MddFormTextareaComponent } from 'src/app/components/mdd-form-textarea/mdd-form-textarea.component';
import { FormatOptionsPipe } from 'src/app/pipes/format-options.pipe';

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
