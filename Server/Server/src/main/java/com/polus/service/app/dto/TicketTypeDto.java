package com.polus.service.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketTypeDto {

	private int adminId;
	private String typeName;
	private String typeDescription;
}
