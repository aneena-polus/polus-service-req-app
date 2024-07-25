package com.polus.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polus.service.app.entities.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Integer> {
}
