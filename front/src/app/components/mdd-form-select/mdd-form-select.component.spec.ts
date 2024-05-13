import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MddFormSelectComponent } from './mdd-form-select.component';

describe('MddFormSelectComponent', () => {
  let component: MddFormSelectComponent;
  let fixture: ComponentFixture<MddFormSelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MddFormSelectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MddFormSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
