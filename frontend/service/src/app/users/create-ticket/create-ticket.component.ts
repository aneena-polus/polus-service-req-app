import { Component } from '@angular/core';
import { Login, ServiceType, CreateTicket, ShowTicket } from './ServiceType';
import { DataService } from '../../data.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
   styleUrl: './create-ticket.component.css'
})

export class CreateTicketComponent {

    errorMap = new Map<string, string>();
    serviceTypes: ServiceType[] = [];
    createdTicket: ShowTicket | undefined;
    serviceType: number = 0;
    serviceDesc: string = '';
    employeeId: number | undefined;
    loggedInUser: Login = {} as Login;
    isFormSubmitted: boolean = false;

	constructor( private _data_service: DataService,
                private _snackbar: MatSnackBar ) { }

    ngOnInit(): void {
        this.getServiceTypes();
        this.loggedInUser = this._data_service.getLoggedInUser();
        this.employeeId = this.loggedInUser.employeeId;
    }

    public getServiceTypes(): void {
        this._data_service.getServiceTypes().subscribe((data: ServiceType[]) => {
            this.serviceTypes = data.sort((a, b) => a.ticketType.localeCompare(b.ticketType));
        });
    }

    public createTicket(): void {
        this.errorMap.clear();
        !this.serviceType ? this.displayErrorMessage('ticketTypeMessage', 'Please select a ticket type.') : null;
        !this.serviceDesc ? this.displayErrorMessage('serviceDescMessage', 'Please enter description.') : null;
        const TICKETRO: CreateTicket = {
            ticketType: this.serviceType,
            ticketCreateBy: this.employeeId,
            ticketDescription: this.serviceDesc,
        };
        if (!this.errorMap.has('ticketTypeMessage') && !this.errorMap.has('serviceDescMessage')) {
            this._data_service.createTicket( TICKETRO ).subscribe({
                next: (response: ShowTicket) => {
                  console.log(response);
                    this.serviceType = 0;
                    this.serviceDesc = '';
                    this.createdTicket = response;
                    this.isFormSubmitted = true;
                    response? this.openCreateTicketMessage() : console.log('No response received');
                },
                error: (error) => {
                    console.error(error);
                },
            });
        }
	  }

    private displayErrorMessage( key: string, value: string ): void {
        this.errorMap.set(key, value);
      }

    private openCreateTicketMessage(): void {
        this._snackbar.open(`Ticket Created Successfully.`, undefined, {
            duration: 2000
        });
    }
}

