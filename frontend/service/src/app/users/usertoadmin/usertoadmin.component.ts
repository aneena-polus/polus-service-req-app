import { Component } from '@angular/core';
import { DataService } from '../../data.service';
import { UserToAdmin, MakeAdmin } from '../create-ticket/ServiceType';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-usertoadmin',
  templateUrl: './usertoadmin.component.html',
  styleUrl: './usertoadmin.component.css'
})

export class UsertoadminComponent {

    usersLists: UserToAdmin[] = [];
    makeAdmin: number = 0;

    constructor ( private _data_service: DataService,
                private _snackbar: MatSnackBar,
                private _dialogRef : MatDialogRef<UserToAdmin> ) {}

    ngOnInit(): void {
        this._data_service.userToAdmin().subscribe(( data: UserToAdmin[] ) => {
            this.usersLists = data.sort((a, b) => a.firstName.localeCompare( b.firstName ));
        });
    }

    public onCancel(): void {
        this._dialogRef.close();
    }

    public makeUserAdmin(): void {
        const ADMINRO: MakeAdmin = {
            employeeId: this.makeAdmin,
            roleId: 2
        };
        this._data_service.makeAdmin( ADMINRO ).subscribe({
            next: () => {
                this.openMessage('User is made to admin Successfully!');
                this._dialogRef.close();
            },
            error: (error) => {
              console.error(error);
            },
        });
    }

    private openMessage( message: string ): void {
        this._snackbar.open( message, undefined, {
          duration: 2000
        });
    }

}
