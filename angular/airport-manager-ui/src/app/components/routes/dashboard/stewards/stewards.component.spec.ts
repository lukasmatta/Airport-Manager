import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StewardsComponent } from './stewards.component';

describe('StewardsComponent', () => {
  let component: StewardsComponent;
  let fixture: ComponentFixture<StewardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StewardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StewardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
