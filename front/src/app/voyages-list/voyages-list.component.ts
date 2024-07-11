import { Component } from '@angular/core';
import { VoyageServiceService } from '../services/voyage-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-voyages-list',
  templateUrl: './voyages-list.component.html',
  styleUrls: ['./voyages-list.component.css']
})
export class VoyagesListComponent {
  onSubmitDelete() {
    this.voyageService.deletevoyage(this.toDelete).subscribe(
      () => {
        console.log('voyage deleted successfully');
        // Remove the deleted voyage from the voyages array
        this.voyages = this.voyages.filter(u => u.id !== this.toDelete);
      },
      (error) => {
        console.error('Error deleting voyage:', error);
      }
    );
  }
    toDelete:any;
    voyages: any[] = [];
    editForm: FormGroup; // Reactive Form for editing voyage
    selectedvoyage: any = {};
    updateRequest: any;
    updatedvoyage: any;
  
    constructor(private voyageService: VoyageServiceService, private fb: FormBuilder) { 
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
      this.fetchvoyages();
    }
  
    fetchvoyages(): void {
      this.voyageService.getAllvoyages().subscribe(
        (voyages) => {
          this.voyages = voyages;
          console.log(voyages);
        },
        (error) => {
          console.error('Error fetching voyages:', error);
        }
      );
    }
  
    editvoyage(voyage: any): void {
      this.selectedvoyage = { ...voyage };
      console.log('Editing voyage:', voyage);
      this.editForm.patchValue({
        firstName: this.selectedvoyage.firstName,
        lastName: this.selectedvoyage.lastName,
        email: this.selectedvoyage.email
      });
      this.selectedvoyage =voyage.id
    }
  
    deletevoyage(id: number): void {
      this.toDelete=id;
    }
  
    
    
  openUpdateModal(voyage: any) {
    //console.log(formation);
    this.updateRequest = this.fb.group({
      firstName: [voyage.firstName, Validators.required],
      lastName: [voyage.lastName, Validators.required],
      email: [voyage.email, [Validators.required, Validators.email]]
    });
  }
  onSubmitUpdate(): void {
    const updatedVoyage: any = this.editForm.value;
    // Call service to update voyage
    this.voyageService.updatevoyage(updatedVoyage.id,updatedVoyage)
      .subscribe(() => {
        // Handle success or error
        console.log('Voyage updated successfully');
        // Close modal if needed
        document.getElementById('exampleModal')?.classList.remove('show');
        document.body.classList.remove('modal-open');
        document.body.style.removeProperty('padding-right');
      });
  }
  
}
