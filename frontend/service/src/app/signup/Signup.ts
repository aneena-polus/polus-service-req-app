export interface Signup {
    employeeId?: number;
    firstname: string;
    lastname: string;
    email: string;
    designation: string;
    state: string;
    countryCode: string;
    phoneNumber: number;
    username: string;
    password: string;
}

export interface Country {
    countryCode: string;
    countryName: string;
    currencyCode: string;
    updateTimestamp: Date;
    updateUser: string;
    countryCodeIso2: string;
};
