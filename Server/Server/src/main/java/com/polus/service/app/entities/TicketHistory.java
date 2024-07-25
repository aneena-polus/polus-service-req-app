package com.polus.service.app.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SR_TICKET_HISTORY")
public class TicketHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_HISTORY_ID")
	private int ticketHistoryId;

	@Column(name = "TICKET_ID", nullable = false)
	private int ticketId;

	@ManyToOne
	@JoinColumn(name = "TICKET_STATUS", nullable = false)
	private TicketStatus ticketStatus;

	@ManyToOne
	@JoinColumn(name = "UPDATED_BY", nullable = false)
	private Employee updatedBy;

	@Column(name = "UPDATE_TIMESTAMP", nullable = false, updatable = false)
	private Timestamp updateTimestamp;
}
