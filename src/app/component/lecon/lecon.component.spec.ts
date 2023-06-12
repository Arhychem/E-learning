import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeconComponent } from './lecon.component';

describe('LeconComponent', () => {
  let component: LeconComponent;
  let fixture: ComponentFixture<LeconComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LeconComponent]
    });
    fixture = TestBed.createComponent(LeconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
