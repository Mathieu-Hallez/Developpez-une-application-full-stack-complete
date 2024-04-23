import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MddFormInputComponent } from './mdd-form-input.component';

describe('MddFormInputComponent', () => {
  let component: MddFormInputComponent;
  let fixture: ComponentFixture<MddFormInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MddFormInputComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MddFormInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
