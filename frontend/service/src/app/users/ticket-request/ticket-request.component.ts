import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AssignTicketComponent } from '../assign-ticket/assign-ticket.component';
import { DataService } from '../../data.service';
import { ServiceType, GetTicketRequest, EditTicketRequest, ShowTicket, TicketCount } from '../create-ticket/ServiceType';
import { Login } from '../../login/Login';
import { DatePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { PageEvent } from '@angular/material/paginator';

@Component({
    selector: 'app-ticket-request',
    templateUrl: './ticket-request.component.html',
    styleUrl: './ticket-request.component.css',
    providers: [DatePipe]
})

export class TicketRequestComponent {

    @ViewChild('closebutton') closebutton: any;

    serviceTypes: ServiceType[] = [];
    ticketRequests: GetTicketRequest[] = [];
    filteredTicketRequests: GetTicketRequest[] = [];
    selectedTicket: GetTicketRequest | null = null;
    ticketRequestsAllData: ShowTicket[] = [];
    ticketId: number = 0;
    ticketType: number = 0;
    ticketStatusId: number = 0;
    ticketDesc: string = '';
    loggedInUser: Login = {} as Login;
    serviceType: number = 0;
    serviceDesc: string = '';
    activeStatus: number = 1;
    ProgressTicketCount: number = 0;
    AssignedTicketCount: number = 0;
    ApprovedTicketCount: number = 0;
    RejectedTicketCount: number = 0;
    currentPage: number = 0;
    showFirstLastButtons = true;
    pageSize: number = 3;

    constructor( private _data_service: DataService,
                 private _dialogref: MatDialog,
                 private _datePipe: DatePipe,
                 private _snackbar: MatSnackBar ) { }

    ngOnInit(): void {
        this.loggedInUser = this._data_service.getLoggedInUser();
        this.getServiceTypes();
        this.fetchTicketCount();
        this.inProgressStatus(1);
        this._data_service.getServiceTypes().subscribe((data) => {
            this.serviceTypes = data;
        });
    }

    public editTicket( ticketRequest: GetTicketRequest ): void {
        this.initialiseTicket(ticketRequest);
    }

    public editSubmit(): void {
        const TICKETRO: EditTicketRequest = {
            ticketId: this.ticketId,
            ticketType: this.serviceType,
            ticketDescription: this.serviceDesc,
            ticketCreateBy: this.loggedInUser.employeeId ?? 0,
            employeeid: null,
            adminId: null,
            statusId: this.activeStatus
        };
        this._data_service.editTicket( TICKETRO ).subscribe({
            next: () => {
                this.serviceType = 0;
                this.serviceDesc = '';
                this.closebutton.nativeElement.click();
                this.openTicketOperationMessage('Ticket Updated Successfully!');
                this.activeStatus === 1? this.inProgressStatus(1): null;
                this.activeStatus === 4? this.rejectStatus(4): null;
            },
            error: (error) => {
                console.error(error);
            },
        });
    }

    public deleteTicket( ticketRequest: GetTicketRequest ): void {
        this.ticketId = ticketRequest.id;
        this.ticketDesc = ticketRequest.ticketDescription;
        let dialogRef = this._dialogref.open(ConfirmDialogComponent, {
            width: '700px',
            panelClass: 'custom-dialog-container',
            data: {
                    ticketId: this.ticketId,
                    ticketDesc: this.ticketDesc
                }
        });
        dialogRef.afterClosed().subscribe( deleteConfirm => {
            if ( deleteConfirm === 'confirm' ) {
                this._data_service.deleteTicket( this.ticketId ).subscribe({
                    next: () => {
                        this.openTicketOperationMessage('Ticket Deleted Successfully!');
                        this.fetchTicketCount();
                        this.inProgressStatus(1);
                    },
                    error: (error) => {
                        console.error(error);
                    },
                });
            }
        });
    }

    public assignTicket( ticketRequest: GetTicketRequest ): void {
        this.initialiseTicket(ticketRequest);
        let dialogRef = this._dialogref.open( AssignTicketComponent, {
            width: '700px',
            panelClass: 'custom-dialog-container',
            data: {
                ticketId: this.ticketId,
                employeeId: this.loggedInUser.employeeId
            }
        });
        dialogRef.afterClosed().subscribe(() => {
            this.fetchTicketCount();
            this.activeStatus === 1? this.inProgressStatus(1): null;
            this.activeStatus === 4? this.rejectStatus(4): null;
        });
    }

    public viewMoreTicket( ticketRequest: GetTicketRequest ): void {
        this.selectedTicket = ticketRequest;
    }

    private initialiseTicket( ticketRequest: GetTicketRequest ): void {
        this.ticketId = ticketRequest.id;
        this.ticketType = ticketRequest.ticketType.ticketTypeId;
        this.ticketDesc = ticketRequest.ticketDescription;
        this.serviceType = ticketRequest.ticketType.ticketTypeId;
        this.serviceDesc = ticketRequest.ticketDescription;
    }

    public getServiceTypes(): void {
        this._data_service.getServiceTypes().subscribe((data: ServiceType[]) => {
            this.serviceTypes = data.sort((a, b) => a.ticketType.localeCompare(b.ticketType));
        });
    }

    public inProgressStatus( status: number ): void {
        this.activeStatus = status;
        this.currentPage = 0;
        this.fetchTicketRequests(1);
    }

    public assignStatus( status: number ): void {
        this.activeStatus = status;
        this.currentPage = 0;
        this.fetchTicketRequests(2);
    }

    public approveStatus( status: number ): void {
        this.activeStatus = status;
        this.currentPage = 0;
        this.fetchTicketRequests(3);
    }

    public rejectStatus( status: number ): void {
        this.activeStatus = status;
        this.currentPage = 0;
        this.fetchTicketRequests(4);
    }

    public fetchTicketRequests( statusId: number ): void {
        this._data_service.fetchTicketRequests(statusId, this.currentPage, this.pageSize).subscribe({
            next: (ticketRequests: GetTicketRequest[]) => {
                this.filteredTicketRequests = ticketRequests;
                this.ticketRequests = ticketRequests;
            },
            error: (error) => {
                console.error('Error fetching ticket requests:', error);
            },
        });
    }

    public filterResults( filterValue: string ): void {
        filterValue = filterValue.toLowerCase();
        this.filteredTicketRequests = this.ticketRequests.filter(ticketRequest => {
            const assignedTo = ticketRequest.assignedTo ? `${ticketRequest.assignedTo.firstName} ${ticketRequest.assignedTo.lastName}`.toLowerCase() : '';
            const serviceType = ticketRequest.ticketType.ticketType.toLowerCase();
            const description = ticketRequest.ticketDescription.toLowerCase();
            const createdDate = this._datePipe.transform(ticketRequest.ticketCreateTimestamp, 'MMM d, y, h:mm:ss a')?.toLowerCase() || '';
            const editedDate = this._datePipe.transform(ticketRequest.ticketUpdateTimestamp, 'MMM d, y, h:mm:ss a')?.toLowerCase() || '';
            const comments = ticketRequest.comment && ticketRequest.comment.length > 0 ? ticketRequest.comment.map(c => c.comment.toLowerCase()).join(' ') : '';
            return assignedTo.includes(filterValue) ||
                serviceType.includes(filterValue) ||
                description.includes(filterValue) ||
                createdDate?.includes(filterValue) ||
                editedDate?.includes(filterValue) ||
                comments.includes(filterValue);
        });
    }

    public onPageChange( event: PageEvent ): void {
        this.currentPage = event.pageIndex;
        this.pageSize = event.pageSize;
        this.fetchTicketRequests(this.activeStatus);
    }

    public fetchTicketCount(): void {
        this._data_service.fetchTicketCount().subscribe({
            next: (response: TicketCount) => {
                this.ProgressTicketCount = response.InProgressCount;
                this.AssignedTicketCount = response.AssignedCount;
                this.ApprovedTicketCount = response.ApprovedCount;
                this.RejectedTicketCount = response.RejectedCount;
            },
            error: (error) => {
                console.error('Error fetching ticket requests:', error);
            },
        });
    }

    private openTicketOperationMessage(message: string): void {
        this._snackbar.open(message, undefined, {
            duration: 2000,
            panelClass: 'my-custom-snackbar'
        });
    }

    public getTicketCount(): number {
        switch (this.activeStatus) {
            case 1: return this.ProgressTicketCount;
            case 2: return this.AssignedTicketCount;
            case 3: return this.ApprovedTicketCount;
            case 4: return this.RejectedTicketCount;
            default: return 0;
        }
    }
}
