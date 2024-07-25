package com.polus.service.app.dto;

import java.sql.Timestamp;
import java.util.List;

import com.polus.service.app.entities.TicketType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketResponseDto {

	private int id;
    private TicketType ticketType;
    private String ticketDescription;
	private Timestamp ticketCreateTimestamp;
	private Timestamp ticketUpdateTimestamp;
	private Integer ticketStatus;
	private AdminDto assignedTo;
	private String createBy;
	private List<TicketCommentDto> comment;
	private Integer count;
}
