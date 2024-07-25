package com.polus.service.app.dto;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketCommentDto {

	private int commentId;
	private String comment;
	private String createBy;
	private Timestamp timestamp;
}
