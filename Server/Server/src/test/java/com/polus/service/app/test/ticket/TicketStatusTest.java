package com.polus.service.app.test.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.polus.service.app.dto.TicketStatusDto;
import com.polus.service.app.entities.Employee;
import com.polus.service.app.entities.TicketStatus;
import com.polus.service.app.repository.TicketStatusRepository;
import com.polus.service.app.services.TicketStatusService;

@ExtendWith(MockitoExtension.class)
public class TicketStatusTest {

	@Mock
	TicketStatusRepository ticketStatusRepository;
	@InjectMocks
	TicketStatusService ticketStatusService;

	@Test
	public void testStatusFound() {

		TicketStatusDto status1 = new TicketStatusDto(1, "in-progress", "Description", "Manesh");
		TicketStatusDto status2 = new TicketStatusDto(2, "Assigned", "Description", "Manesh");
		java.util.List<TicketStatusDto> expectedResult = Arrays.asList(status1, status2);

		Employee employee = new Employee();
		employee.setFirstname("Manesh");
		TicketStatus ticketStatus1 = new TicketStatus(1, "in-progress", "Description",
				Timestamp.valueOf("2024-07-15 11:36:58"), employee);
		TicketStatus ticketStatus2 = new TicketStatus(2, "Assigned", "Description",
				Timestamp.valueOf("2024-07-15 11:36:58"), employee);
		List<TicketStatus> ticketStatus = Arrays.asList(ticketStatus1, ticketStatus2);

		when(ticketStatusRepository.findAll()).thenReturn(ticketStatus);

		java.util.List<TicketStatusDto> actualResult = ticketStatusService.getAllTicketStatuses();
		assertEquals(expectedResult, actualResult);
	}
	@Test
	public void testStatusNotFound() {
		when(ticketStatusRepository.findAll()).thenReturn(Collections.emptyList());
		List<TicketStatusDto> actualResult = ticketStatusService.getAllTicketStatuses();
		assertEquals(0, actualResult.size());
	}
}
