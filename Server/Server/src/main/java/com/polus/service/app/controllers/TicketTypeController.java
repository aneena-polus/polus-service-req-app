package com.polus.service.app.controllers;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.polus.service.app.dto.TicketTypeDto;
import com.polus.service.app.entities.TicketType;
import com.polus.service.app.services.TicketTypeService;

@RestController
@RequestMapping("/api/ticket")
public class TicketTypeController {

	private Logger logger = LogManager.getLogger(TicketTypeController.class);

	@Autowired
	TicketTypeService ticketTypeService;

	@GetMapping("/getalltickettype")
	public ResponseEntity<Object> getAllTicketType() {
		logger.info("Request to fetch all ticket service types.");
		List<TicketType> ticketType = ticketTypeService.getAllTicketType();
		if (ticketType == null) {
			logger.info("Ticket service types not found.");
			ResponseEntity.notFound().build();
		}
		logger.info("Ticket service types found.");
		return ResponseEntity.ok(ticketType);
	}

	@PostMapping("/createtickettype")
	public ResponseEntity<String> createTicketType(@RequestBody TicketTypeDto ticketTypeDto) {
		logger.info("Request to create an ticket service type.");
		if (ticketTypeService.createTicketType(ticketTypeDto)) {
			logger.info("New ticket type created successfully.");
			return ResponseEntity.status(HttpStatus.OK).body("New ticket type created successfully");
		} else {
			logger.info("New ticket type creation failed.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket type creation failed");
		}
	}
}
