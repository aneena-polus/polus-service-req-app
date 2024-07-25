package com.polus.service.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketStatusChangeDto {

	private Integer adminId;
	private Integer ticketId;
	private Integer statusId;
	private String comments;
}
