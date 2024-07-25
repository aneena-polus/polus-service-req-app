import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Login } from '../../login/Login';
import { DataService } from '../../data.service';

@Component({
	selector: 'app-user-home',
	templateUrl: './user-home.component.html',
	styleUrl: './user-home.component.css'
})

export class UserHomeComponent {

    loggedInUser: Login = {} as Login;
    role: string = '';
	activeStatus: string = 'createTicket';

	constructor( public dialog: MatDialog,
                 private _data_service: DataService ) {}

    ngOnInit(): void {
        this.loggedInUser = this._data_service.getLoggedInUser();
        if ( this.loggedInUser && this.loggedInUser.roles ) {
			this.loggedInUser.roles.forEach( element => {
                element.roleId === 2 ? this.role='admin': null;
        	});
      	}
    }

    public setActiveStatus( status: string ): void {
        this.activeStatus = status;
    }

}
