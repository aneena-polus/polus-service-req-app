package com.polus.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polus.service.app.entities.TicketStatus;

public interface TicketStatusRepository extends JpaRepository<TicketStatus, Integer>{
}
