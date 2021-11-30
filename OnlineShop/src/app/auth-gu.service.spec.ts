import { TestBed } from '@angular/core/testing';

import { AuthGuService } from './auth-gu.service';

describe('AuthGuService', () => {
  let service: AuthGuService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthGuService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
