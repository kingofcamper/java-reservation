import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { VoyagesListComponent } from './voyages-list/voyages-list.component';
import { ReservationListComponent } from './reservation-list/reservation-list.component';

const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'voyages', component: VoyagesListComponent },
  { path: 'reservation', component: ReservationListComponent },

  // Add other routes here
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }

