import { TestBed } from '@angular/core/testing';

import { SytemeService } from './syteme.service';

describe('SytemeService', () => {
  let service: SytemeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SytemeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
