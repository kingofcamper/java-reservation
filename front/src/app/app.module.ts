import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; // Import HttpClientModule
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user-list.component';
import { VoyagesListComponent } from './voyages-list/voyages-list.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReservationListComponent } from './reservation-list/reservation-list.component';


@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    VoyagesListComponent,
    ReservationListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    BrowserAnimationsModule,
    MatNativeDateModule, // Import MatNativeDateModule here

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
