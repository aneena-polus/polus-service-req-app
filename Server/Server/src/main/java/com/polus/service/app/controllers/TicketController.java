package com.polus.service.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.polus.service.app.dto.GenerateOrUpdateDto;
import com.polus.service.app.dto.TicketResponseDto;
import com.polus.service.app.dto.TicketStatusChangeDto;
import com.polus.service.app.repository.TicketRepository;
import com.polus.service.app.repository.TicketTypeRepository;
import com.polus.service.app.services.TicketService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

	private Logger logger = LogManager.getLogger(TicketController.class);

	@Autowired
	TicketService ticketService;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TicketTypeRepository ticketTypeRepository;

	@PostMapping("/generateticket")
	public ResponseEntity<Object> generateTicket(@RequestBody GenerateOrUpdateDto genearateTicket) {
		logger.info("Request to generate or update a ticket.");
		return ResponseEntity.ok(ticketService.generateOrUpdateTicket(genearateTicket));
	}

	@DeleteMapping("/deleteticket/{id}")
	public ResponseEntity<Map<String, String>> deleteTicket(@PathVariable int id) {
		logger.info("Request to delete ticket based on ticket id.");
		Map<String, String> message = new HashMap<>();
		boolean isDeleted = ticketService.deleteTicket(id);
		if (!isDeleted) {
			logger.info("Ticket deletion failed.");
			message.put("Message", "Ticket deletion failed.");
			return ResponseEntity.ok(message);
		} else {
			logger.info("Ticket deleted successfully.");
			message.put("Message", "Ticket deleted Successfully.");
		}
		return ResponseEntity.ok(message);
	}

	@PostMapping("/assignadmin")
	public ResponseEntity<Map<String, String>> assignAdminToTicket(@RequestBody GenerateOrUpdateDto generateTicketDto) {
		logger.info("Request to assign an admin to a ticket.");
		Map<String, String> message = new HashMap<>();
		if (ticketService.assignAdminToTicket(generateTicketDto)) {
			logger.info("Admin assigned successfully.");
			message.put("Message", "Admin assigned successfully.");
			return ResponseEntity.ok(message);
		} else {
			logger.info("Admin assigning failed.");
			message.put("Message", "Admin assigning failed.");
			return ResponseEntity.ok(message);
		}
	}

	@GetMapping("/response/{employeeId}/{statusId}/{pageNumber}/{pageSize}")
	public List<TicketResponseDto> ticketResponse(@PathVariable Integer employeeId, @PathVariable Integer statusId,
			@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
		logger.info("Request to fetch tickets based on employee and status.");
		return ticketService.ticketResponse(employeeId, statusId, pageNumber, pageSize);
	}

	@PostMapping("/statuschange")
	public ResponseEntity<Map<String, String>> changeTicketStatus(@RequestBody TicketStatusChangeDto statusChange) {
		logger.info("Request to change status of a ticket.");
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.changeStatusToApproveOrReject(statusChange));
	}

	@GetMapping("/assignedtome/{adminId}/{pageNumber}/{pageSize}")
	public ResponseEntity<Object> getAssignedToMeTickets(@PathVariable int adminId, @PathVariable Integer pageNumber,
			@PathVariable Integer pageSize) {
		logger.info("Request to get tickets assigned to an admin.");
		return ResponseEntity.ok(ticketService.getAssignedToMeTickets(adminId, pageNumber, pageSize));
	}

	@GetMapping("/ticketcount/{employeeId}")
	public ResponseEntity<Map<String, Integer>> getTicketCount(@PathVariable Integer employeeId) {
		logger.info("Request to fetch count of tickets based on employee and status");
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.getTicketsCount(employeeId));
	}
}
