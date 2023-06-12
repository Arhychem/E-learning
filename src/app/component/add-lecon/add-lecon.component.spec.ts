import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLeconComponent } from './add-lecon.component';

describe('AddLeconComponent', () => {
  let component: AddLeconComponent;
  let fixture: ComponentFixture<AddLeconComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddLeconComponent]
    });
    fixture = TestBed.createComponent(AddLeconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
