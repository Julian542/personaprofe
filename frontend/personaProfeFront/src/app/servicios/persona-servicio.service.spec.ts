import { TestBed } from '@angular/core/testing';

import { PersonaServicioService } from './persona-servicio.service';

describe('PersonaServicioService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PersonaServicioService = TestBed.get(PersonaServicioService);
    expect(service).toBeTruthy();
  });
});
