import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MddFormTextareaComponent } from './mdd-form-textarea.component';

describe('MddFormTextareaComponent', () => {
  let component: MddFormTextareaComponent;
  let fixture: ComponentFixture<MddFormTextareaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MddFormTextareaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MddFormTextareaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
