package com.polus.service.app.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "sr_tickets")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SR_TICKET_ID")
	private int ticketId;

	@ManyToOne
	@JoinColumn(name = "SR_TICKET_TYPE")
	private TicketType ticketType;

	@Column(name = "SR_DESCRIPTION")
	private String ticketDescription;

	@ManyToOne
	@JoinColumn(name = "SR_STATUS_ID")
	private TicketStatus ticketStatusId;

	@CreatedDate
	@Column(name = "SR_CREATE_TIMESTAMP")
	private Timestamp ticketCreateTimestamp;
	
	@ManyToOne
	@JoinColumn(name = "SR_CREATE_BY")
	private Employee ticketCreateBy;
	
	@ManyToOne
	@JoinColumn(name = "SR_ASSIGNED_TO")
	private Employee ticketAssignedTo;
	
	@LastModifiedDate
	@Column(name = "SR_UPDATED_TIMESTAMP")
	private Timestamp ticketUpdateTimestamp;
}
