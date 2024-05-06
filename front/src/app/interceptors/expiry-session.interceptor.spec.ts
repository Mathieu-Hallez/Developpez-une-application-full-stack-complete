import { TestBed } from '@angular/core/testing';

import { ExpirySessionInterceptor } from './expiry-session.interceptor';

describe('ExpirySessionInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      ExpirySessionInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: ExpirySessionInterceptor = TestBed.inject(ExpirySessionInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
