package com.polus.service.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateOrUpdateDto {
	
	private Integer ticketId;
	private Integer ticketType;
	private String ticketDescription;
	private Integer ticketCreateBy;
	private Integer employeeid;
	private Integer adminId; 
}
