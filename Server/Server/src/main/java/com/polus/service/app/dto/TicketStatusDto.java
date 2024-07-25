package com.polus.service.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketStatusDto {

	private int statusId;
	private String statusName;
	private String statusDescription;
	private String createBy;
}
