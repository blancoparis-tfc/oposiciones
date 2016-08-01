/* tslint:disable:no-unused-variable */

import { addProviders, async, inject } from '@angular/core/testing';
import { OposicionesService } from './oposiciones.service';

describe('Service: Oposiciones', () => {
  beforeEach(() => {
    addProviders([OposicionesService]);
  });

  it('should ...',
    inject([OposicionesService],
      (service: OposicionesService) => {
        expect(service).toBeTruthy();
      }));
});
