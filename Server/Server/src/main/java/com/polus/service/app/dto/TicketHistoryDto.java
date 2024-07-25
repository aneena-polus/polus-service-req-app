package com.polus.service.app.dto;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketHistoryDto {

	private Integer ticketHistoryId;
	private Integer ticketId;
	private Integer ticketStatus;
	private Integer updatedBy;
	private Timestamp updateTimestamp;
}
