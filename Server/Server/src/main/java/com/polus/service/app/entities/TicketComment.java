package com.polus.service.app.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import org.springframework.data.annotation.CreatedDate;
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
@Table(name = "sr_ticket_comment")
public class TicketComment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_COMMENT_ID")
	private int commentId;

	@ManyToOne
	@JoinColumn(name = "TICKET_ID")
	private Ticket ticketId;

	@Column(name = "TICKET_COMMENT")
	private String ticketComment;

	@CreatedDate
	@Column(name = "TICKET_COMMENT_CREATE_TIMESTAMP")
	private Timestamp createTimestamp;

	@ManyToOne
	@JoinColumn(name = "TICKET_COMMENT_CREATE_BY")
	private Employee createBy;
}
