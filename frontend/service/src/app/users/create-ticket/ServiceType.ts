export interface ServiceType {
	ticketTypeId: number;
	ticketType: string;
	ticketTypeDescription: string;
	ticketTypeCreateTimestamp: Date;
	ticketTypeCreatedBy: number;
};

export interface SignUpResponse {
    Message: string;
    Username: string;
    Password: string;
}

export interface EditResponse {
    employeeId: number;
    username: string;
    password: string;
    firstname: string;
    lastname: string;
    email: string;
    designation: string;
    roles:null;
    state: string;
    countryCode: Country;
    phoneNumber: number;
    createdDate: null;
}

export interface Login {
	employeeId?: number;
	firstname?: string;
	lastname?: string;
	email?: string;
	designation?: string;
	roles?: Role[];
	state?: string;
	country?: string;
	countryCode?: Country;
	phoneNumber?: number;
	username: string;
	password: string;
	employeeCreatedDate?: number;
};

export interface Role {
	roleId: number;
	roleName: string;
	roleDescription: string;
};

export interface Country {
    countryCode: string;
    countryName: string;
    currencyCode: string;
    updateTimestamp: Date;
    updateUser: string;
    countryCodeIso2: string;
};

export interface CreateTicket {
	ticketType: number;
	ticketCreateBy?: number;
	ticketDescription: string;
    ticketId:null,
    adminId:null,
    statusId:1
};

export interface GetTicketRequest {
	id: number;
	ticketType: TicketTypes;
	ticketDescription: string;
	ticketCreateTimestamp: Date;
    ticketUpdateTimestamp?: Date;
    ticketStatus?: number;
    assignedTo?: AdminList;
    createBy: null;
    comment?: Comment[];
};

export interface TicketTypes {
	ticketTypeId: number;
	ticketType: string;
	ticketTypeDescription: string;
	ticketTypeCreateTimestamp: Date;
	ticketTypeCreatedBy: number;
};

export interface AdminList {
	employeeId: number;
	firstName: string;
	lastName: string;
    roles: Role[];
};

export interface EditTicketRequest {
    ticketId: number;
    ticketType: number;
    ticketDescription: string;
    ticketCreateBy: number;
    employeeid: null,
    adminId: null;
    statusId: number;
};

export interface AssignAdmin {
    ticketId : number;
  	ticketType :null,
    ticketDescription: null,
    ticketCreateBy: null,
    employeeid: null,
    adminId: number;
    statusId: null;
};

export interface GetAssignedTickets {
    id: number;
    ticketType: TicketTypes;
    ticketDescription: string;
    ticketCreateTimestamp: Date;
    ticketUpdateTimestamp?: Date;
    ticketStatus: number;
    assignedTo: null;
    createBy: string;
    comment: Comment[];
    count: number;
};

export interface PerformAction {
    adminId: number;
    ticketId: number;
    statusId: number;
    comments: string;
};

export interface ShowTicket {
	id: number;
	ticketType: TicketTypes;
	ticketDescription: string;
	ticketCreateTimestamp: Date;
    ticketUpdateTimestamp?: Date;
    ticketStatus?: number;
    assignedTo?: AdminList;
    createBy?: string;
    comment?: Comment;
};

export interface UserToAdmin {
    employeeId: number;
    firstName: string;
    lastName: string;
    roles: Role[];
    selected?: boolean;
};

export interface MakeAdmin {
    adminId?: number;
    employeeId: number;
    roleId: number;
};

export interface CreateServiceType {
    adminId?: number;
    typeName: string;
    typeDescription: string;
};

export interface Response {
    [key: string]: any;
};

export interface TicketCount {
    InProgressCount: number;
    AssignedCount: number;
    ApprovedCount: number;
    RejectedCount: number;
    AssignedToMeCount?: number;
};

export interface Comment {
    commentId: number;
    comment: string;
    createBy: string;
    timestamp: Date;
};
