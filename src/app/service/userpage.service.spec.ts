import { TestBed } from '@angular/core/testing';

import { UserpageService } from './userpage.service';

describe('UserpageService', () => {
  let service: UserpageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserpageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
