import { Component, Inject } from '@angular/core';
import { DataService } from '../../data.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AdminDashboardComponent } from '../admin-dashboard/admin-dashboard.component';
import { GetAssignedTickets, PerformAction } from '../create-ticket/ServiceType';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
    selector: 'app-statuschange',
    templateUrl: './statuschange.component.html',
    styleUrl: './statuschange.component.css'
})

export class StatuschangeComponent {

    comment: string = '';
    statusId: number = 0;
    errorMessage: string | null = null;

    constructor( private _data_service: DataService,
                 private _dialogRef : MatDialogRef<AdminDashboardComponent>,
                 private _snackbar: MatSnackBar,
                 @Inject(MAT_DIALOG_DATA) public data: { ticketRequests: GetAssignedTickets, statusId: number } ) { }

    ngOnInit(): void {
        this.statusId= this.data.statusId;
    }

    public performAction(): void {
        if ( this.comment == '' ) {
			this.errorMessage = 'Fields cannot be blank';
		}
        else {
            const TICKETRO: PerformAction = {
                adminId:this._data_service.loggedInUser.employeeId ?? 0,
                ticketId:this.data.ticketRequests.id,
                statusId:this.data.statusId,
                comments:this.comment
            };
            this._data_service.approveTicket( TICKETRO ).subscribe({
                next: () => {
                    this.statusId == 3? this.openTicketOperationMessage('Ticket is approved Successfully!') : null;
                    this.statusId == 4? this.openTicketOperationMessage('Ticket is rejected Successfully!') : null;
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

    public onCancel(): void {
        this._dialogRef.close();
    }

}
