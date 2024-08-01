import { Component } from '@angular/core';
import { DataService } from '../../data.service';
import { UserToAdmin, MakeAdmin, AdminList, Login } from '../create-ticket/ServiceType';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-usertoadmin',
  templateUrl: './usertoadmin.component.html',
  styleUrl: './usertoadmin.component.css'
})

export class UsertoadminComponent {

    usersLists: UserToAdmin[] = [];
    adminLists: AdminList[] = [];
    loggedInUser: Login = {} as Login;
    makeAdmin: number = 0;
    makeUser: number = 0;

    constructor ( private _data_service: DataService,
                  private _snackbar: MatSnackBar,
                  private _dialogRef : MatDialogRef<UserToAdmin> ) {}

    ngOnInit(): void {
        this.loggedInUser = this._data_service.getLoggedInUser();
        this.adminList();
        this.userList();
    }

    public assignRole(): void {
        const ADMINRO: MakeAdmin = {
            employeeId: this.makeAdmin,
            roleId: 2
        };
        this._data_service.makeAdmin( ADMINRO ).subscribe({
            next: () => {
                this.openMessage('User role is assigned successfully!');
                this.adminList();
                this.userList();
            },
            error: (error) => {
                console.error(error);
            },
        });
    }

    public revokeRole(): void {
        const USERRO: MakeAdmin = {
            adminId: this.loggedInUser.employeeId,
            employeeId: this.makeUser,
            roleId: 2
        };
        this._data_service.revokeRole( USERRO ).subscribe({
            next: () => {
                this.openMessage('Admin role is revoked successfully!');
                this.adminList();
                this.userList();
            },
            error: (error) => {
                console.error(error);
            },
        });
    }

    private adminList(): void {
        this._data_service.adminList().subscribe(( data: AdminList[] ) => {
            this.adminLists = data.sort((a, b) => a.firstName.localeCompare(b.firstName));
        });
    }

    private userList(): void {
        this._data_service.userToAdmin().subscribe(( data: UserToAdmin[] ) => {
            this.usersLists = data.sort((a, b) => a.firstName.localeCompare( b.firstName ));
        });
    }

    private openMessage( message: string ): void {
        this._snackbar.open( message, undefined, {
            duration: 2000,
            panelClass: 'my-custom-snackbar'
        });
    }

    public onCancel(): void {
        this._dialogRef.close();
    }

}
