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
@Table(name = "sr_ticket_status")
public class TicketStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_STATUS_ID")
	private int statusId;
	
	@Column(name = "TICKET_STATUS_NAME")
	private String statusName;
	
	@Column(name = "TICKET_STATUS_DESCRIPITION")
	private String statusDescription;
	
	@Column(name = "TICKET_STATUS_CREATE_TIMESTAMP", insertable=false, updatable=false)
	private Timestamp createTimestamp;
	
	@ManyToOne
	@JoinColumn(name = "TICKET_STATUS_CREATE_BY")
	private Employee createBy;
}
