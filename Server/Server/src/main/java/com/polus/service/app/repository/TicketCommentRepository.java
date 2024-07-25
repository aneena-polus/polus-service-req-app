package com.polus.service.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.polus.service.app.entities.TicketComment;

public interface TicketCommentRepository extends JpaRepository<TicketComment, Integer>{

	@Query(value="SELECT * FROM sr_ticket_comment WHERE ticket_id = :ticketId", nativeQuery = true)
	List<TicketComment> findByTicketId(@Param("ticketId") Integer ticketId);
}
