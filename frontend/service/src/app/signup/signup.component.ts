import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RouterOutlet, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgClass } from '@angular/common';
import { DataService } from '../data.service';
import { CommonModule } from '@angular/common';
import { Country, Signup } from './Signup';
import { SignUpResponse } from '../users/create-ticket/ServiceType';

@Component({
    selector: 'app-signup',
    standalone: true,
    imports: [RouterOutlet, RouterLink, NgClass, FormsModule, CommonModule],
    templateUrl: './signup.component.html',
    styleUrl: './signup.component.css'
})

export class SignupComponent {

    errorMap = new Map<string, string>();
    passwordFieldType: string = 'password';
    isPasswordVisible: boolean = false;
    username: string = '';
    password: string = '';
    firstname: string = '';
    lastname: string = '';
    email: string = '';
    phoneNumber?: number;
    designation: string = '';
    state: string = '';
    country: string = '';
    countries: Country[] = [];
    isFormSubmitted: boolean = true;
    isResponseSent: boolean = true;
    alreadyExistErrorMessage: string | null = null;

	constructor( private _DATA_SERVICE: DataService,
				 private _ROUTER: Router,
        		 private _SNACKBAR: MatSnackBar ) { }

	ngOnInit(): void {
		this.getCountries();
	}

	public signup(): void {
		this.isResponseSent = true;
		this.errorMap.clear();
		const signupRO: Signup = {
			firstname: this.firstname,
			lastname: this.lastname,
			email: this.email,
			designation: this.designation,
			state: this.state,
			countryCode: this.country,
			phoneNumber: this.phoneNumber? this.phoneNumber:0,
			username: this.username,
			password: this.password
		};

		!this.username ? this.displayErrorMessage('usernameErrorMessage', 'Please enter a valid username.') : null;
		!this.password ? this.displayErrorMessage('passwordErrorMessage', 'Please enter a valid password.') : null;
		!this.firstname ? this.displayErrorMessage('firstNameErrorMessage', 'Please enter a valid first name.') : null;
		!this.lastname ? this.displayErrorMessage('lastNameErrorMessage', 'Please enter a valid last name.') : null;
		!this.isValidEmail(this.email) ? this.displayErrorMessage('emailErrorMessage', 'Please enter a valid email.[eg:user@gmail.com]') : null;
		!this.isValidPhoneNumber(this.phoneNumber?this.phoneNumber:0) ? this.displayErrorMessage('phoneErrorMessage', 'Please enter a valid phone number[10 digits]') : null;
		!this.designation ? this.displayErrorMessage('designationErrorMessage', 'Please enter a valid designation.'): null;
		!this.state ? this.displayErrorMessage('stateErrorMessage', 'Please enter a valid state name.') : null;
		!this.country ? this.displayErrorMessage('countryErrorMessage', 'Please select a valid country name.') : null;

		if ( this.isResponseSent ) {
			this._DATA_SERVICE.signup(signupRO).subscribe({
				next: (response: SignUpResponse) => {
                    this._ROUTER.navigate(['/login']);
                    this._DATA_SERVICE.setSignedUpUser(response);
                    this.openSignUpMessage();
				},
				error: (error) => {
					console.error(error);
					this.alreadyExistErrorMessage = 'Username already exists';
				},
			});
		}
    }

	private displayErrorMessage(key:string, value:string): void {
		this.errorMap.set(key, value);
		this.isResponseSent = false;
	}

    private isValidEmail(email: string): boolean {
        const emailRegex = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
        return emailRegex.test(email);
    }

    public togglePasswordVisibility(): void {
        this.isPasswordVisible = !this.isPasswordVisible;
        this.passwordFieldType = this.isPasswordVisible ? 'text' : 'password';
    }

    private isValidPhoneNumber(phoneNumber: number): boolean {
        const phoneNumberPattern = /^[1-9]\d{9}$/;
        return phoneNumberPattern.test( phoneNumber.toString() );
    }

    public getCountries(): void {
        this._DATA_SERVICE.getCountries().subscribe((data: Country[]) => {
            this.countries = data.sort((a, b) => a.countryName.localeCompare(b.countryName));
        });
    }

	private openSignUpMessage(): void {
		this._SNACKBAR.open(`Sign Up Successful. Please Login.`, undefined, {
			duration: 2000,
            panelClass: 'my-custom-snackbar'
		});
	}

}

