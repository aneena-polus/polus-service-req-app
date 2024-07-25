package com.polus.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.polus.service.app.entities.TicketHistory;

@Transactional
public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Integer> {

	void deleteByTicketId(int ticketId);
}
