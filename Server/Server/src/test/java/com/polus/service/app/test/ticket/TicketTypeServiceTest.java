package com.polus.service.app.test.ticket;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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

import com.polus.service.app.dto.TicketTypeDto;
import com.polus.service.app.entities.TicketType;
import com.polus.service.app.repository.TicketTypeRepository;
import com.polus.service.app.services.TicketTypeService;

@ExtendWith(MockitoExtension.class)
public class TicketTypeServiceTest {

	@Mock
	TicketTypeRepository ticketTypeRepository;
	@InjectMocks
	TicketTypeService ticketTypeService;
	
	@Test
	public void testTicketTypeFound(){

		TicketType ticketType1 = new TicketType(1, "Software", "Support", Timestamp.valueOf("2024-08-05 12:34:56.789"), 1);
		TicketType ticketType2 = new TicketType(1, "Hardware", "Support", Timestamp.valueOf("2024-08-05 12:34:56.789"), 2);
		List<TicketType> expectedResult = Arrays.asList(ticketType1, ticketType2);
		when(ticketTypeRepository.findAll()).thenReturn(expectedResult);
		List<TicketType> actualResult = ticketTypeService.getAllTicketType();
		assertEquals(expectedResult, actualResult);
		assertEquals(2, actualResult.size());
	}
	
	@Test
	public void testTicketTypeEmptyList() {
		when(ticketTypeRepository.findAll()).thenReturn(Collections.emptyList());
		List<TicketType> ticketType = ticketTypeService.getAllTicketType();
		assertEquals(0, ticketType.size());
	}

	@Test
	public void testCreateTicketType() {
		TicketTypeDto typeDto = new TicketTypeDto(1, "Training" , "Support");
		TicketType expectedResult = new TicketType(typeDto.getTypeName(), typeDto.getTypeDescription(), Timestamp.valueOf("2024-08-05 12:34:56.789"), typeDto.getAdminId());
		when(ticketTypeRepository.save(any(TicketType.class))).thenReturn(expectedResult);
		Boolean actualResult = ticketTypeService.createTicketType(typeDto);
		assertTrue(actualResult);
	}
}
