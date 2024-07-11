import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoyagesListComponent } from './voyages-list.component';

describe('VoyagesListComponent', () => {
  let component: VoyagesListComponent;
  let fixture: ComponentFixture<VoyagesListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VoyagesListComponent]
    });
    fixture = TestBed.createComponent(VoyagesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
