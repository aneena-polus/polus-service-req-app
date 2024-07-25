package com.polus.service.app.dto;

import java.sql.Timestamp;
import com.polus.service.app.entities.TicketType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketAdminAssignedDto {

	private int id;
    private TicketType ticketType;
    private String ticketDescription;
	private Timestamp ticketCreateTimestamp;
	private Timestamp ticketUpdateTimestamp;
	private String ticketStatus;
	private AdminDto assignedTo;
}