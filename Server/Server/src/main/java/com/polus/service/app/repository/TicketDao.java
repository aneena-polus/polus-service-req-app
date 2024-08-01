package com.polus.service.app.repository;

import com.polus.service.app.entities.TicketStatus;
import com.polus.service.app.entities.TicketType;


public interface TicketDao {

	TicketStatus findByStatusName(String statusName);
	TicketType findByTypeName(String typeName);
}
