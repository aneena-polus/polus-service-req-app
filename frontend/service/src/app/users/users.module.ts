import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsersRoutingModule } from './users-routing.module';
import { UserHomeComponent } from './user-home/user-home.component';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';
import { TicketRequestComponent } from './ticket-request/ticket-request.component';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog'
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { AssignTicketComponent } from './assign-ticket/assign-ticket.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { StatuschangeComponent } from './statuschange/statuschange.component';
import { TruncatePipe } from './truncate.pipe';
import { UsertoadminComponent } from './usertoadmin/usertoadmin.component';
import { CreateserviceComponent } from './createservice/createservice.component';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatBadgeModule } from '@angular/material/badge';

@NgModule({
  declarations: [
    UserHomeComponent,
    CreateTicketComponent,
    TicketRequestComponent,
    AssignTicketComponent,
    AdminDashboardComponent,
    StatuschangeComponent,
    UsertoadminComponent,
    CreateserviceComponent,
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    FormsModule,
    MatDialogModule,
    MatSnackBarModule,
    TruncatePipe,
    MatButtonModule,
    MatPaginatorModule,
    MatBadgeModule
  ]
})

export class UsersModule { }
