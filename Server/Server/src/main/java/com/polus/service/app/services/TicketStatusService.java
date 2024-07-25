package com.polus.service.app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.polus.service.app.dto.TicketStatusDto;
import com.polus.service.app.entities.TicketStatus;
import com.polus.service.app.repository.TicketStatusRepository;

@Service
public class TicketStatusService {

	private Logger logger = LogManager.getLogger(TicketStatusService.class);

	@Autowired
	TicketStatusRepository ticketStatusRepository;

	public List<TicketStatusDto> getAllTicketStatuses() {
		try {
			logger.info("Service method to fetch all ticket status details");
			List<TicketStatus> ticketStatuses = ticketStatusRepository.findAll();
			if (ticketStatuses.isEmpty()) {
				return Collections.emptyList();
			}
			List<TicketStatusDto> statuses = new ArrayList<>();
			for (TicketStatus status : ticketStatuses) {
				TicketStatusDto statusDto = new TicketStatusDto();
				statusDto.setStatusId(status.getStatusId());
				statusDto.setStatusName(status.getStatusName());
				statusDto.setStatusDescription(status.getStatusDescription());
				statusDto.setCreateBy(status.getCreateBy().getFirstname());
				statuses.add(statusDto);
			}
			return statuses;
		} catch (DataAccessException e) {
			logger.error("Error in fetching ticket statuses", e);
			throw new RuntimeException(e.getMessage());
		}
	}
}
