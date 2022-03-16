import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterFormReaktiveComponent } from './register-form-reaktive.component';

describe('RegisterFormReaktiveComponent', () => {
  let component: RegisterFormReaktiveComponent;
  let fixture: ComponentFixture<RegisterFormReaktiveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterFormReaktiveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterFormReaktiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
