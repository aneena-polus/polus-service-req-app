package com.polus.service.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.polus.service.app.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query(value = "SELECT * FROM sr_tickets WHERE SR_CREATE_BY = :employeeId AND SR_STATUS_ID = :statusId ORDER BY sr_updated_timestamp DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
	List<Ticket> findByEmployeeIdWithTickets(@Param("employeeId") Integer employeeId,
			@Param("statusId") Integer statusId, @Param("limit") int pageSize, @Param("offset") int offset);

	@Query(value = "SELECT COUNT(*) FROM sr_tickets WHERE SR_CREATE_BY = :employeeId AND SR_STATUS_ID = :statusId", nativeQuery = true)
	Integer countByEmployeeIdAndStatusId(@Param("employeeId") Integer employeeId, @Param("statusId") Integer statusId);

	@Query(value = "SELECT * FROM sr_tickets WHERE SR_ASSIGNED_TO = :adminId LIMIT :limit OFFSET :offset", nativeQuery = true)
	List<Ticket> findByAssignedTo(@Param("adminId") Integer adminId, @Param("limit") Integer pageSize,
			@Param("offset") Integer offset);

	@Query(value = "SELECT COUNT(*) FROM sr_tickets  WHERE SR_ASSIGNED_TO = :adminId", nativeQuery = true)
	Integer countOfTicketByAdminId(@Param("adminId") Integer adminId);
}
