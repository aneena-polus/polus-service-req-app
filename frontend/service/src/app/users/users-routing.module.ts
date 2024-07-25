import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { TicketRequestComponent } from './ticket-request/ticket-request.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';

const routes: Routes = [
    {	path: '', redirectTo: 'userhome', pathMatch: 'full'	},
    {	path: 'userhome', component: UserHomeComponent,
		children: [
			{	path: '', redirectTo: 'createticket', pathMatch: 'full'	},
			{   path: 'createticket', component: CreateTicketComponent 	},
			{   path: 'ticketrequest', component: TicketRequestComponent  },
			{   path: 'admindashboard', component: AdminDashboardComponent 	},
    	]},
    {   path: '**', redirectTo: '/login' }
];

@NgModule({
  imports: [ RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})

export class UsersRoutingModule { }
