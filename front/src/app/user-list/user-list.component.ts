import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../services/user-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})

export class UserListComponent implements OnInit {
  addedUser: any;
onSubmitAdd() {
  if (this.addForm) {
    this.addedUser = {
      firstName: this.addForm.get('firstName')?.value,
      lastName: this.addForm.get('lastName')?.value,
      email: this.addForm.get('email')?.value,
    };
  }

  console.log(this.addedUser);
  this.userService.createUser(this.addedUser).subscribe(
    res => {
      this.fetchUsers();
      console.log("here")
    },
    error => {
      console.error('Error updating  :', error);
    }
  );
}
onSubmitDelete() {
  this.userService.deleteUser(this.toDelete).subscribe(
    () => {
      console.log('User deleted successfully');
      // Remove the deleted user from the users array
      this.users = this.users.filter(u => u.id !== this.toDelete);
    },
    (error) => {
      console.error('Error deleting user:', error);
    }
  );
}
  toDelete:any;
  users: any[] = [];
  editForm: FormGroup; // Reactive Form for editing user
  addForm: FormGroup; // Reactive Form for editing user
  selectedUser: any = {};
  updateRequest: any;
  updatedUser: any;

  constructor(private userService: UserServiceService, private fb: FormBuilder) { 
    this.editForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
    this.addForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(): void {
    this.userService.getAllUsers().subscribe(
      (users) => {
        this.users = users;
        console.log(users);
      },
      (error) => {
        console.error('Error fetching users:', error);
      }
    );
  }

  editUser(user: any): void {
    this.selectedUser = { ...user };
    console.log('Editing user:', user);
    this.editForm.patchValue({
      firstName: this.selectedUser.firstName,
      lastName: this.selectedUser.lastName,
      email: this.selectedUser.email
    });
    this.selectedUser =user.id
  }

  deleteUser(id: number): void {
    this.toDelete=id;
  }

  
  
openUpdateModal(user: any) {
  //console.log(formation);
  this.updateRequest = this.fb.group({
    firstName: [user.firstName, Validators.required],
    lastName: [user.lastName, Validators.required],
    email: [user.email, [Validators.required, Validators.email]]
  });
}
onSubmitUpdate() {
  if (this.editForm) {
    this.updatedUser = {
      firstName: this.editForm.get('firstName')?.value,
      lastName: this.editForm.get('lastName')?.value,
      email: this.editForm.get('email')?.value,
    };
  }
  console.log(this.updatedUser);
  this.userService.updateUser(this.selectedUser, this.updatedUser).subscribe(
    res => {
      this.fetchUsers();
      console.log("here")
    },
    error => {
      console.error('Error updating  :', error);
    }
  );
}
}
