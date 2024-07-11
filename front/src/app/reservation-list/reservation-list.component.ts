import { Component } from '@angular/core';
import { ReservationServiceService } from '../services/reservation-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-reservations-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent {
  onSubmitDelete() {
    this.reservationService.deletereservation(this.toDelete).subscribe(
      () => {
        console.log('reservation deleted successfully');
        // Remove the deleted reservation from the reservations array
        this.reservations = this.reservations.filter(u => u.id !== this.toDelete);
      },
      (error) => {
        console.error('Error deleting reservation:', error);
      }
    );
  }
    toDelete:any;
    reservations: any[] = [];
    editForm: FormGroup; // Reactive Form for editing reservation
    selectedreservation: any = {};
    updateRequest: any;
    updatedreservation: any;
  
    constructor(private reservationService: ReservationServiceService, private fb: FormBuilder) { 
      this.editForm = this.fb.group({
        id: [''],
        name: [''],
        destination: [''],
        price: [''],
        tripDate: [''],
        startDate: [''],
        endDate: ['']
      });
    }
  
    ngOnInit(): void {
      this.fetchreservations();
    }
  
    fetchreservations(): void {
      this.reservationService.getAllreservations().subscribe(
        (reservations) => {
          this.reservations = reservations;
          console.log(reservations);
        },
        (error) => {
          console.error('Error fetching reservations:', error);
        }
      );
    }
  
    editreservation(reservation: any): void {
      this.selectedreservation = { ...reservation };
      console.log('Editing reservation:', reservation);
      this.editForm.patchValue({
        firstName: this.selectedreservation.firstName,
        lastName: this.selectedreservation.lastName,
        email: this.selectedreservation.email
      });
      this.selectedreservation =reservation.id
    }
  
    deletereservation(id: number): void {
      this.toDelete=id;
    }
  
    
    
  openUpdateModal(reservation: any) {
    //console.log(formation);
    this.updateRequest = this.fb.group({
      firstName: [reservation.firstName, Validators.required],
      lastName: [reservation.lastName, Validators.required],
      email: [reservation.email, [Validators.required, Validators.email]]
    });
  }
  onSubmitUpdate(): void {
    const updatedreservation: any = this.editForm.value;
    // Call service to update reservation
    this.reservationService.updatereservation(updatedreservation.id,updatedreservation)
      .subscribe(() => {
        // Handle success or error
        console.log('reservation updated successfully');
        // Close modal if needed
        document.getElementById('exampleModal')?.classList.remove('show');
        document.body.classList.remove('modal-open');
        document.body.style.removeProperty('padding-right');
      });
  }
}
