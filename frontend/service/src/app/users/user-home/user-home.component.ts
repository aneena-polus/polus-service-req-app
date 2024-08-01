import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Login } from '../../login/Login';
import { DataService } from '../../data.service';
import { Signup } from '../../signup/Signup';
import { Country, EditResponse } from '../create-ticket/ServiceType';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
	selector: 'app-user-home',
	templateUrl: './user-home.component.html',
	styleUrl: './user-home.component.css'
})

export class UserHomeComponent {

    loggedInUser: Login = {} as Login;
    role: string = '';
    firstname: string = '';
    isEditClicked: boolean = false;
    edittedDetails: EditResponse = {} as EditResponse;
	activeStatus: string = 'createTicket';
    lastname: string = '';
    email: string = '';
    phoneNumber: string = '';
    designation: string = '';
    state: string = '';
    country: string = '';
    countries: Country[] = [];

	constructor( public dialog: MatDialog,
                 private _data_service: DataService,
                 private _snackbar: MatSnackBar ) {}

    ngOnInit(): void {
        this.getCountries();
        this.loggedInUser = this._data_service.getLoggedInUser();
        if ( this.loggedInUser && this.loggedInUser.roles ) {
			this.loggedInUser.roles.forEach( element => {
                element.roleId === 2 ? this.role='admin': null;
        	});
      	}
    }

    public editUserDetails(): void {
        this.isEditClicked = true;
        this.firstname = this.edittedDetails.firstname? this.edittedDetails.firstname : this.loggedInUser.firstname ?? '';
        this.lastname = this.edittedDetails.lastname? this.edittedDetails.lastname : this.loggedInUser.lastname ?? '';
        this.email = this.edittedDetails.email? this.edittedDetails.email : this.loggedInUser.email ?? '';
        this.phoneNumber = this.edittedDetails.phoneNumber? this.edittedDetails.phoneNumber.toString() : this.loggedInUser.phoneNumber?.toString() ?? '';
        this.designation = this.edittedDetails.designation? this.edittedDetails.designation : this.loggedInUser.designation ?? '';
        this.state = this.edittedDetails.state? this.edittedDetails.state : this.loggedInUser.state ?? '';
        this.country = this.edittedDetails.countryCode? this.edittedDetails.countryCode.countryCode : this.loggedInUser.countryCode?.countryCode ?? '';
    }

    public editSubmit(): void {
        const USERRO: Signup = {
            employeeId: this.loggedInUser.employeeId,
            firstname: this.firstname,
            lastname: this.lastname,
            email: this.email,
            designation: this.designation,
            state: this.state,
            countryCode: this.country,
            phoneNumber: Number(this.phoneNumber),
            username: this.loggedInUser.username,
            password: this.loggedInUser.password
        }
        this._data_service.editDetails( USERRO ).subscribe({
            next: ( response: EditResponse ) => {
                this.edittedDetails = response;
                this.isEditClicked = false;
                this.openTicketOperationMessage('User Details Updated Successfully!');
            },
            error: (error) => {
                console.error(error);
            },
        });
    }

    public setActiveStatus( status: string ): void {
        this.activeStatus = status;
    }

    public getCountries(): void {
        this._data_service.getCountries().subscribe((data: Country[]) => {
        this.countries = data.sort((a, b) => a.countryName.localeCompare(b.countryName));
        });
    }

    public onClose(): void {
        this.isEditClicked = false;
    }

    private openTicketOperationMessage(message: string): void {
        this._snackbar.open(message, undefined, {
            duration: 2000,
            panelClass: 'my-custom-snackbar'
        });
    }

}
