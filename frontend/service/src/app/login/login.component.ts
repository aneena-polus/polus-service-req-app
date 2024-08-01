import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgClass } from '@angular/common';
import { DataService } from '../data.service';
import { SignUpResponse } from '../users/create-ticket/ServiceType';

@Component({
	selector: 'app-login',
	standalone: true,
	imports: [RouterOutlet, RouterLink, NgClass, FormsModule, CommonModule],
	templateUrl: './login.component.html',
	styleUrl: './login.component.css'
})

export class LoginComponent {

	passwordFieldType: string = 'password';
	isPasswordVisible: boolean = false;
	username: string = 'sasikumar';
	password: string = 'sasikumarpassword';
    signedUpUser: SignUpResponse = {} as SignUpResponse;
	errorMessage: string | null = null;

	constructor( private _DATA_SERVICE: DataService,
				 private _ROUTER: Router,
        		 private _SNACKBAR: MatSnackBar ) { }

    ngOnInit(): void {
        this.signedUpUser = this._DATA_SERVICE.getSignedUpUser();
        this.username = this.signedUpUser.Username;
        this.password = this.signedUpUser.Password;
    }

	public login(): void {
		if ( this.isBlankField() ) {
			this.errorMessage = 'Fields cannot be blank';
		}
		else {
			this._DATA_SERVICE.login({
				username: this.username,
				password: this.password
			}).subscribe({
				next: ( response ) => {
					if( response && response.roles ) {
						const adminRole = response.roles.find( roles => roles.roleId === 1 );
						if( adminRole ) {
							this._ROUTER.navigate(['/users']);
                            this._DATA_SERVICE.setLoggedInUser(response);
              				this.openLoginMessage(response.username);
						}
						else {
							this._ROUTER.navigate(['/admin']);
						}
					}
				},
				error: ( error ) => {
					console.log(error);
					this.errorMessage = 'Invalid username or password';
				},
			});
		}
	}

	public togglePasswordVisibility(): void {
		this.isPasswordVisible = !this.isPasswordVisible;
		this.passwordFieldType = this.isPasswordVisible ? 'text' : 'password';
	}

	private isBlankField(): boolean {
		return (this.username == '' || this.password == '');
	}

	private openLoginMessage(username: string): void {
		this._SNACKBAR.open(`Welcome, ${username}`, undefined, {
			duration: 2000,
            horizontalPosition: 'center',
            verticalPosition: 'top',
            panelClass: 'my-custom-snackbar-login'
		});
	}
}
