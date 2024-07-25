package com.polus.service.app.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.polus.service.app.Constants;
import com.polus.service.app.dto.AdminDto;
import com.polus.service.app.dto.GenerateOrUpdateDto;
import com.polus.service.app.dto.TicketCommentDto;
import com.polus.service.app.dto.TicketResponseDto;
import com.polus.service.app.dto.TicketStatusChangeDto;
import com.polus.service.app.entities.Employee;
import com.polus.service.app.entities.Ticket;
import com.polus.service.app.entities.TicketComment;
import com.polus.service.app.entities.TicketHistory;
import com.polus.service.app.entities.TicketStatus;
import com.polus.service.app.entities.TicketType;
import com.polus.service.app.repository.EmployeeRepository;
import com.polus.service.app.repository.TicketCommentRepository;
import com.polus.service.app.repository.TicketHistoryRepository;
import com.polus.service.app.repository.TicketRepository;
import com.polus.service.app.repository.TicketStatusRepository;
import com.polus.service.app.repository.TicketTypeRepository;

@Service
public class TicketService {

	private Logger logger = LogManager.getLogger(TicketService.class);

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TicketTypeRepository ticketTypeRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	TicketHistoryRepository ticketHistoryRepository;

	@Autowired
	TicketStatusRepository ticketStatusRepository;

	@Autowired
	TicketCommentRepository ticketCommentRepository;

	public Object generateOrUpdateTicket(GenerateOrUpdateDto generateTicket) {
		Map<String, String> message = new HashMap<>();
		Ticket ticket = new Ticket();
		TicketStatus status = ticketStatusRepository.findById(Constants.INPROGRESS_STATUS_ID)
				.orElseThrow(() -> new RuntimeException("Status not found"));
		try {
			Optional<TicketType> ticketType = ticketTypeRepository.findById(generateTicket.getTicketType());
			Optional<Employee> employee = employeeRepository.findById(generateTicket.getTicketCreateBy());
			if (generateTicket.getTicketId() != null) {
				Ticket updateTicket = ticketRepository.findById(generateTicket.getTicketId())
						.orElseThrow(() -> new RuntimeException("Ticket not found"));
				saveOrUpdateTicket(updateTicket, ticketType, generateTicket, employee, status);
				saveInHistoryTable(updateTicket, employee); // Save ticket in history table
				message.put("Message", "Ticket updated successfully");
				return message;
			} else {
				saveOrUpdateTicket(ticket, ticketType, generateTicket, employee, status);
				TicketResponseDto response = new TicketResponseDto(); // Ticket Response
				response.setId(ticket.getTicketId());
				response.setTicketType(ticket.getTicketType());
				response.setTicketDescription(ticket.getTicketDescription());
				response.setCreateBy(ticket.getTicketCreateBy().getFullname());
				response.setTicketStatus(ticket.getTicketStatusId().getStatusId());
				response.setTicketCreateTimestamp(ticket.getTicketCreateTimestamp());
				saveInHistoryTable(ticket, employee); // Save ticket in history table
				return response;
			}
		} catch (Exception e) {
			logger.info("Error in generating or updating ticket: {}", e.getMessage());
			message.put("Message", "Failed");
			return message;
		}
	}

	public List<TicketResponseDto> ticketResponse(Integer employeeId, Integer statusId, Integer pageNumber,
			Integer pageSize) {
		Integer offset = pageNumber * pageSize;
		List<TicketResponseDto> response = new ArrayList<>();
		Employee adminList = new Employee();
		if (statusId != null) {
			List<Ticket> tickets = ticketRepository.findByEmployeeIdWithTickets(employeeId, statusId, pageSize, offset);
			for (Ticket ticket : tickets) {
				TicketResponseDto responseDto = new TicketResponseDto();
				responseDto.setId(ticket.getTicketId());
				responseDto.setTicketType(ticket.getTicketType());
				responseDto.setTicketDescription(ticket.getTicketDescription());
				responseDto.setTicketCreateTimestamp(ticket.getTicketCreateTimestamp());
				responseDto.setTicketUpdateTimestamp(ticket.getTicketUpdateTimestamp());
				responseDto.setTicketStatus(ticket.getTicketStatusId().getStatusId());
				if (Constants.INPROGRESS_STATUS_ID != statusId) {
					adminList = employeeRepository.findById(ticket.getTicketAssignedTo().getEmployeeId())
							.orElseThrow(() -> new RuntimeException("Admin not found"));
					AdminDto list = new AdminDto();
					list.setRole_id(adminList.getEmployeeId());
					list.setFirstName(adminList.getFirstname());
					list.setLastName(adminList.getLastname());
					responseDto.setAssignedTo(list);
					if (Constants.ASSIGNED_STATUS_ID != statusId) {
						List<TicketComment> ticketComment = ticketCommentRepository
								.findByTicketId(ticket.getTicketId());
						responseDto.setComment(getTicketComments(ticketComment));
					}
				}
				response.add(responseDto);
			}
		}
		return response;
	}

	public boolean deleteTicket(int ticketId) {
		if (ticketRepository.existsById(ticketId)) {
			ticketHistoryRepository.deleteByTicketId(ticketId);
			ticketRepository.deleteById(ticketId);
			return true;
		} else
			return false;
	}

	public boolean assignAdminToTicket(GenerateOrUpdateDto generateTicketDto) {
		if (ticketRepository.existsById(generateTicketDto.getTicketId())) {
			Ticket ticket = ticketRepository.findById(generateTicketDto.getTicketId())
					.orElseThrow(() -> new RuntimeException("Ticket not found."));
			Employee admin = employeeRepository.findById(generateTicketDto.getAdminId())
					.orElseThrow(() -> new RuntimeException("Employee not found."));
			TicketStatus status = ticketStatusRepository.findById(Constants.ASSIGNED_STATUS_ID).orElseThrow();
			ticket.setTicketAssignedTo(admin);
			ticket.setTicketStatusId(status);
			ticket.setTicketUpdateTimestamp(Timestamp.from(Instant.now()));
			ticketRepository.save(ticket);
			return true;
		}
		return false;
	}

	public List<TicketResponseDto> getAssignedToMeTickets(Integer adminId, Integer pageNumber, Integer pageSize) {
		try {
			Integer offset = pageNumber * pageSize;
			List<TicketResponseDto> ResponseList = new ArrayList<>();
			List<Ticket> tickets = ticketRepository.findByAssignedTo(adminId, pageSize, offset);
			for (Ticket ticket : tickets) {
				TicketResponseDto dto = new TicketResponseDto();
				dto.setId(ticket.getTicketId());
				dto.setTicketType(ticket.getTicketType());
				dto.setTicketDescription(ticket.getTicketDescription());
				dto.setTicketStatus(ticket.getTicketStatusId().getStatusId());
				dto.setTicketCreateTimestamp(ticket.getTicketCreateTimestamp());
				dto.setTicketUpdateTimestamp(ticket.getTicketUpdateTimestamp());
				dto.setCreateBy(ticket.getTicketCreateBy().getFullname());
				if (Constants.ASSIGNED_STATUS_ID != ticket.getTicketStatusId().getStatusId()) {
					List<TicketComment> ticketComments = ticketCommentRepository.findByTicketId(ticket.getTicketId());
					dto.setComment(getTicketComments(ticketComments));
				}
				ResponseList.add(dto);
				dto.setCount(ticketRepository.countOfTicketByAdminId(adminId));
			}
			return ResponseList;
		} catch (DataAccessException e) {
			logger.error("Error in fetching tickets: {}", e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public Map<String, String> changeStatusToApproveOrReject(TicketStatusChangeDto statusChangeDto) {
		try {
			Map<String, String> message = new HashMap<>();
			Employee admin = employeeRepository.findById(statusChangeDto.getAdminId())
					.orElseThrow(() -> new RuntimeException("Employee not found."));
			Ticket ticket = ticketRepository.findById(statusChangeDto.getTicketId())
					.orElseThrow(() -> new RuntimeException("Ticket not found."));
			if (statusChangeDto.getStatusId().equals(Constants.APPROVED_STATUS_ID)) {
				TicketStatus status = ticketStatusRepository.findById(Constants.APPROVED_STATUS_ID).orElseThrow();
				ticket.setTicketStatusId(status);
				ticketRepository.save(ticket);
				message.put("Message", "Ticket approved successfully");
			}
			if (statusChangeDto.getStatusId().equals(Constants.REJECTED_STATUS_ID)) {
				TicketStatus status = ticketStatusRepository.findById(Constants.REJECTED_STATUS_ID).orElseThrow();
				ticket.setTicketStatusId(status);
				ticketRepository.save(ticket);
				message.put("Message", "Ticket rejected successfully");
			}
			if (statusChangeDto.getComments() != null) {
				TicketComment comment = new TicketComment();
				comment.setTicketId(ticket);
				comment.setTicketComment(statusChangeDto.getComments());
				comment.setCreateBy(admin);
				comment.setCreateTimestamp(Timestamp.from(Instant.now()));
				ticketCommentRepository.save(comment);
			}
			return message;
		} catch (Exception e) {
			logger.error("Error in changing status: {}", e.getMessage());
			throw new RuntimeException(e);
		}

	}

	public Map<String, Integer> getTicketsCount(Integer employeeId) {
		try {
			Map<String, Integer> ticketCount = new HashMap<>();
			ticketCount.put("InProgressCount",
					ticketRepository.countByEmployeeIdAndStatusId(employeeId, Constants.INPROGRESS_STATUS_ID));
			ticketCount.put("AssignedCount",
					ticketRepository.countByEmployeeIdAndStatusId(employeeId, Constants.ASSIGNED_STATUS_ID));
			ticketCount.put("ApprovedCount",
					ticketRepository.countByEmployeeIdAndStatusId(employeeId, Constants.APPROVED_STATUS_ID));
			ticketCount.put("RejectedCount",
					ticketRepository.countByEmployeeIdAndStatusId(employeeId, Constants.REJECTED_STATUS_ID));
			return ticketCount;
		} catch (Exception e) {
			logger.error("Error in getting tickets count: {}", e.getMessage());
			throw new RuntimeException(e);
		}
	}

	private List<TicketCommentDto> getTicketComments(List<TicketComment> ticketComment) {
		List<TicketCommentDto> comments = new ArrayList<>();
		for (TicketComment comment : ticketComment) {
			TicketCommentDto commentDto = new TicketCommentDto();
			commentDto.setCommentId(comment.getCommentId());
			commentDto.setComment(comment.getTicketComment());
			commentDto.setCreateBy(comment.getCreateBy().getFirstname());
			commentDto.setTimestamp(comment.getCreateTimestamp());
			comments.add(commentDto);
		}
		return comments;
	}

	private void saveInHistoryTable(Ticket ticket, Optional<Employee> employee) {
		TicketHistory history = new TicketHistory();
		history.setTicketId(ticket.getTicketId());
		history.setTicketStatus(ticket.getTicketStatusId());
		history.setUpdateTimestamp(ticket.getTicketUpdateTimestamp());
		history.setUpdatedBy(employee.get());
		ticketHistoryRepository.save(history);
	}

	private void saveOrUpdateTicket(Ticket ticket, Optional<TicketType> ticketType, GenerateOrUpdateDto generateTicket,
			Optional<Employee> employee, TicketStatus status) {
		ticket.setTicketType(ticketType.get());
		ticket.setTicketDescription(generateTicket.getTicketDescription());
		ticket.setTicketCreateBy(employee.get());
		if(!ticketRepository.existsById(ticket.getTicketId()))
			ticket.setTicketCreateTimestamp(Timestamp.from(Instant.now()));
		ticket.setTicketUpdateTimestamp(Timestamp.from(Instant.now()));
		ticket.setTicketStatusId(status);
		ticketRepository.save(ticket);
	}
}
