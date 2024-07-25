package com.polus.service.app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.polus.service.app.services.TicketStatusService;

@RestController
@RequestMapping("/api/ticket")
public class TicketStatusController {

	private Logger logger = LogManager.getLogger(TicketStatusController.class);
	
	@Autowired
	TicketStatusService ticketStatusService;
	
	@GetMapping("/getallticketstatuses")
	public ResponseEntity<Object> getAllTicketStatuses() {
		logger.info("Request to fetch all ticket statuses.");
		return ResponseEntity.ok(ticketStatusService.getAllTicketStatuses());
	}

}
