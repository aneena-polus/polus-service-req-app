import { Component } from '@angular/core';
import { DataService } from '../../data.service';
import { Login, CreateServiceType } from '../create-ticket/ServiceType';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminDashboardComponent } from '../admin-dashboard/admin-dashboard.component';

@Component({
    selector: 'app-createservice',
    templateUrl: './createservice.component.html',
    styleUrl: './createservice.component.css'
})

export class CreateserviceComponent {

    serviceType: string = '';
    serviceDesc: string = '';
    employeeId: number | undefined;
    loggedInUser: Login = {} as Login;

    constructor ( private _data_service: DataService,
                  private _dialogRef : MatDialogRef<AdminDashboardComponent>,
                  private _snackbar: MatSnackBar ) {}

    ngOnInit(): void {
        this.loggedInUser = this._data_service.getLoggedInUser();
        this.employeeId = this.loggedInUser.employeeId;
    }

    public createNewSeviceType(): void {
        const SERVICERO: CreateServiceType = {
            adminId: this.employeeId,
            typeName: this.serviceType,
            typeDescription: this.serviceDesc,
        };
        this._data_service.createNewSeviceType( SERVICERO ).subscribe({
            next: () => {
                this.openCreateTicketMessage();
                this._dialogRef.close();
            },
            error: (error) => {
                console.error(error);
            },
        });
    }

    private openCreateTicketMessage(): void {
        this._snackbar.open(`Service type created successfully.`, undefined, {
            duration: 2000,
            panelClass: 'my-custom-snackbar'
        });
    }

    public onCancel(): void {
        this._dialogRef.close();
    }

}
