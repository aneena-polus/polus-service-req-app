import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DataService } from '../../data.service';
import { GetAssignedTickets, TicketCount } from '../create-ticket/ServiceType';
import { DatePipe } from '@angular/common';
import { StatuschangeComponent } from '../statuschange/statuschange.component';
import { UsertoadminComponent } from '../usertoadmin/usertoadmin.component';
import { CreateserviceComponent } from '../createservice/createservice.component';
import { PageEvent } from '@angular/material/paginator';

@Component({
	selector: 'app-admin-dashboard',
	templateUrl: './admin-dashboard.component.html',
	styleUrl: './admin-dashboard.component.css',
	providers: [DatePipe]
})

export class AdminDashboardComponent {

	activeStatus: string = 'assigned to me';
    ticketRequests: GetAssignedTickets[] = [];
    filteredTicketRequests: GetAssignedTickets[] = [];
    selectedTicket: GetAssignedTickets | null = null ;
    ticketCount: number | undefined;
    currentPage: number = 0;
    filterWord: string = '';
    showFirstLastButtons = true;
    pageSize: number = 3;

    constructor ( private _data_service: DataService,
                  private _datePipe: DatePipe,
                  private _dialogref : MatDialog ) {}

    ngOnInit(): void {
        this.assignedToMe( 'assigned to me' );
        this.fetchTicketCount();
    }

    public assignedToMe( admin: string ): void {
        this.activeStatus = admin;
        this._data_service.assignedToMe( this.currentPage, this.pageSize ).subscribe({
            next: ( ticketRequests: GetAssignedTickets[] ) => {
                this.ticketRequests = ticketRequests;
                this.filteredTicketRequests = ticketRequests;
            },
            error: (error) => {
              console.error('Error fetching ticket requests:', error);
            },
        });
    }

	public onPageChange( event: PageEvent ): void {
        this.currentPage = event.pageIndex;
        this.pageSize = event.pageSize;
        this.assignedToMe('assigned to me');
    }

    public filterResults(filterValue: string): void {
        filterValue = filterValue.toLowerCase();
        this.filteredTicketRequests = this.ticketRequests.filter(ticketRequest => {
        const serviceType = ticketRequest.ticketType.ticketType.toLowerCase();
        const createdBy = ticketRequest.createBy.toLowerCase();
        const description = ticketRequest.ticketDescription.toLowerCase();
        const createdDate = this._datePipe.transform(ticketRequest.ticketCreateTimestamp, 'MMM d, y, h:mm:ss a')?.toLowerCase() || '';
        const editedDate = this._datePipe.transform(ticketRequest.ticketUpdateTimestamp, 'MMM d, y, h:mm:ss a')?.toLowerCase() || '';
        const comments = ticketRequest.comment && ticketRequest.comment.length > 0 ? ticketRequest.comment.map(c => c.comment.toLowerCase()).join(' ') : '';
        return serviceType.includes(filterValue) ||
            description.includes(filterValue) ||
            createdDate?.includes(filterValue) ||
            editedDate?.includes(filterValue) ||
            createdBy?.includes(filterValue) ||
            comments.includes(filterValue);
        });
    }

    public approveTicket( ticketRequest: GetAssignedTickets ): void {
    	let dialogRef= this._dialogref.open( StatuschangeComponent, {
			width: '700px',
			panelClass: 'custom-dialog-container',
			data: {
                    ticketRequests: ticketRequest,
                    statusId: 3
                }
        });
        dialogRef.afterClosed().subscribe(() => {
            this.assignedToMe('assigned to me');
        });
    }

    public rejectTicket( ticketRequest: GetAssignedTickets ): void {
    	let dialogRef= this._dialogref.open(StatuschangeComponent, {
            width: '700px',
            panelClass: 'custom-dialog-container',
            data: {
                    ticketRequests: ticketRequest,
                    statusId: 4
                }
        });
        dialogRef.afterClosed().subscribe(() => {
            this.assignedToMe('assigned to me');
        });
    }

    public fetchTicketCount(): void {
        this._data_service.fetchTicketCount().subscribe({
            next: ( response: TicketCount ) => {
                this.ticketCount = response.AssignedToMeCount;
            },
            error: (error) => {
                console.error('Error fetching ticket requests:', error);
            },
        });
    }

    public roleChange( admin: string ): void {
        this.activeStatus = admin;
        let dialogRef= this._dialogref.open( UsertoadminComponent, {
			width: '1000px',
            maxWidth: '100%',
            minWidth: '50%',
			panelClass: 'custom-dialog-container',
        });
		dialogRef.afterClosed().subscribe(() => {
			this.assignedToMe('assigned to me');
		});
    }

    public createServiceType( admin: string ): void {
        this.activeStatus = admin;
        let dialogRef= this._dialogref.open( CreateserviceComponent, {
            width: '700px',
            panelClass: 'custom-dialog-container',
        });
        dialogRef.afterClosed().subscribe(() => {
            this.assignedToMe('assigned to me');
        });
    }

    public viewMoreTicket( ticketRequest: GetAssignedTickets ): void {
      this.selectedTicket = ticketRequest;
    }
}
