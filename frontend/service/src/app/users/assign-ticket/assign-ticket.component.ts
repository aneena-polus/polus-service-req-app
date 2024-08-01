import { Component, Inject } from '@angular/core';
import { DataService } from '../../data.service';
import { AdminList, AssignAdmin, Login, Role } from '../create-ticket/ServiceType';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
	selector: 'app-assign-ticket',
	templateUrl: './assign-ticket.component.html',
	styleUrl: './assign-ticket.component.css'
})

export class AssignTicketComponent {

    assignAdmin: number = 0;
    isChecked: boolean = false;
    isFormSubmitted: boolean = false;
    assignToMe: number = 0;
    adminLists: AdminList[] = [];
    role: Role | undefined;
    loggedInUser: Login = {} as Login;

    constructor( private _data_service: DataService,
                private _dialogRef : MatDialogRef<AssignTicketComponent>,
                private _snackbar: MatSnackBar,
                @Inject(MAT_DIALOG_DATA) public data: { ticketId: number, employeeId: number } ) { }

    ngOnInit(): void {
        this.adminList();
        this.loggedInUser = this._data_service.getLoggedInUser();
        this.assignToMe = this.data.employeeId;
    }

    public adminList(): void {
        this._data_service.adminList().subscribe(( data: AdminList[] ) => {
            this.adminLists = data.sort((a, b) => a.firstName.localeCompare(b.firstName));
        });
    }

    public assignAdminSubmit(): void {
        this.isFormSubmitted = true;
		const TICKETRO: AssignAdmin = {
			ticketId : this.data.ticketId,
			ticketType :null,
			ticketDescription: null,
			ticketCreateBy: null,
            employeeid: null,
			adminId: this.assignAdmin,
            statusId: null,
		};
        if( this.assignAdmin !==0 ) {
            this._data_service.assignTicket( TICKETRO ).subscribe({
                next: () => {
                    this.openTicketOperationMessage('Ticket is assigned to admin Successfully!');
                    this._dialogRef.close();
                },
                error: (error) => {
                    console.error(error);
                },
            });
        }
    }

    private openTicketOperationMessage( message: string ): void {
        this._snackbar.open( message, undefined, {
            duration: 2000,
            panelClass: 'my-custom-snackbar'
        });
    }

    public assignToMeFun(isChecked: boolean): void {
        isChecked? this.assignAdmin = this.assignToMe : this.assignAdmin = 0;
    }

    public onCancel(): void {
        this._dialogRef.close();
    }
}
