package com.polus.service.app.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polus.service.app.dto.TicketTypeDto;
import com.polus.service.app.entities.TicketType;
import com.polus.service.app.repository.TicketTypeRepository;

@Service
public class TicketTypeService {

	private Logger logger = LogManager.getLogger(TicketTypeService.class);

	@Autowired
	TicketTypeRepository ticketTyperepository;

	public List<TicketType> getAllTicketType() {
		try {
			return ticketTyperepository.findAll();
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	public boolean createTicketType(TicketTypeDto ticketTypeDto) {
		try {
			TicketType ticketType = new TicketType();
			ticketType.setTicketType(ticketTypeDto.getTypeName());
			ticketType.setTicketTypeDescription(ticketTypeDto.getTypeDescription());
			ticketType.setTicketTypeCreatedBy(ticketTypeDto.getAdminId());
			ticketType.setTicketTypeCreateTimestamp(Timestamp.from(Instant.now()));
			ticketTyperepository.save(ticketType);
			return true;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return false;
		}
	}
}
