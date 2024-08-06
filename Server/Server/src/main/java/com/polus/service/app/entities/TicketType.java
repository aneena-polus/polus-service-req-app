package com.polus.service.app.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "sr_ticket_type")
public class TicketType implements Serializable{

	public TicketType() {
		// TODO Auto-generated constructor stub
	}

	public TicketType(String typeName, String typeDescription, Timestamp from, int adminId) {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_TYPE_ID")
	private int ticketTypeId;

	@Column(name = "TICKET_TYPE_NAME")
	private String ticketType;

	@Column(name = "TICKET_TYPE_DESCRIPITION")
	private String ticketTypeDescription;

	@Column(name = "TICKET_TYPE_CREATE_TIMESTAMP")
	private Timestamp ticketTypeCreateTimestamp;

	@Column(name = "TICKET_TYPE_CREATE_BY")
	private int ticketTypeCreatedBy;
}
