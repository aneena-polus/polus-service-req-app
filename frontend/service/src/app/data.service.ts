import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { ServiceType, CreateTicket, GetTicketRequest, EditTicketRequest, UserToAdmin, MakeAdmin,
        AdminList, AssignAdmin, GetAssignedTickets, PerformAction, ShowTicket, Response,
		CreateServiceType, TicketCount} from './users/create-ticket/ServiceType';
import { Login } from './login/Login';
import { Signup, Country } from './signup/Signup';

@Injectable({
	providedIn: 'root'
})

export class DataService {

	loggedInUser: Login = {} as Login;

	constructor( private http: HttpClient ) {}

	login(post: Login): Observable<Login> {
		return this.http.post<Login>('/api/login', post);
	};

	signup(post: Signup): Observable<string> {
		return this.http.post('/api/signup', post, { responseType: 'text' });
	};

	getCountries(): Observable<Country[]> {
		return this.http.get<Country[]>('/api/country');
	};

	getServiceTypes(): Observable<ServiceType[]> {
		return this.http.get<ServiceType[]>('/api/tickettype');
	};

	createTicket(post: CreateTicket): Observable<ShowTicket> {
		return this.http.post<ShowTicket>('/api/createticket', post);
	};

	setLoggedInUser(user: Login): void {
		this.loggedInUser = user;
	};

	getLoggedInUser(): Login {
		return this.loggedInUser;
	};

	fetchTicketCount(): Observable<TicketCount> {
		return this.http.get<TicketCount>(`/api/countticket/${this.loggedInUser.employeeId}`);
	};

	fetchTicketRequests(statusId: number, currentPage: number, pageSize: number): Observable<GetTicketRequest[]> {
		return this.http.get<GetTicketRequest[]>(`/api/getticket/${this.loggedInUser.employeeId}/${statusId}/${currentPage}/${pageSize}`);
	};

	adminList(): Observable<AdminList[]> {
		return this.http.get<AdminList[]>('/api/employeelist/2');
	};

  	editTicket(post: EditTicketRequest): Observable<Response> {
		return this.http.post<Response>('/api/createticket', post);
	};

	deleteTicket(id: number): Observable<void> {
		return this.http.delete<void>(`/api/deleteticket/${id}`);
	};

	assignTicket(post: AssignAdmin): Observable<{ Message: string }> {
		return this.http.post<{ Message: string }>('/api/adminassign', post);
	};

	assignedToMe(currentPage: number, pageSize: number): Observable<GetAssignedTickets[]> {
		return this.http.get<GetAssignedTickets[]>(`/api/assignedtome/${this.loggedInUser.employeeId}/${currentPage}/${pageSize}`);
	};

	approveTicket(post: PerformAction): Observable<{ Message: string }> {
		return this.http.post<{ Message: string }>('/api/statuschange', post);
	};

	userToAdmin(): Observable<UserToAdmin[]> {
		return this.http.get<UserToAdmin[]>(`/api/employeelist/1`);
	};

  	createNewSeviceType(post: CreateServiceType): Observable<string> {
		return this.http.post('/api/newtickettype', post, { responseType: 'text' } );
	};

	makeAdmin(post: MakeAdmin): Observable<string> {
		return this.http.get(`/api/setrole/${post.employeeId}/${post.roleId}`, { responseType: 'text' });
	};

	showTicket(id: number): Observable<ShowTicket[]> {
		return this.http.get<ShowTicket[]>(`/api/getticket/${id}`);
	};

}
