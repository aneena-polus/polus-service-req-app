export interface ServiceType {
	ticketTypeId: number;
	ticketType: string;
	ticketTypeDescription: string;
	ticketTypeCreateTimestamp: Date;
	ticketTypeCreatedBy: number;
};

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
};

export interface AssignAdmin {
    ticketId : number;
  	ticketType :null,
    ticketDescription: null,
    ticketCreateBy: null,
    adminId: number;
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
};

export interface MakeAdmin {
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
}

export interface Comment {
    commentId: number;
    comment: string;
    createBy: string;
    timestamp: Date;
}
