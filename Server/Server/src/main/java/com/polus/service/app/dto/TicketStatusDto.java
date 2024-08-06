package com.polus.service.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TicketStatusDto {

	private int statusId;
	private String statusName;
	private String statusDescription;
	private String createBy;
}
