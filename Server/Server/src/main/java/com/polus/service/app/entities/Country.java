package com.polus.service.app.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import org.springframework.data.annotation.LastModifiedDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "COUNTRY")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "COUNTRY_NAME")
	private String countryName;

	@Column(name = "CURRENCY_CODE")
	private String currencyCode;

	@LastModifiedDate
	@Column(name = "UPDATE_TIMESTAMP")
	private Timestamp updateTimestamp;

	@Column(name = "UPDATE_USER")
	private String updateUser;

	@Column(name = "COUNTRY_CODE_ISO2")
	private String countryCodeIso2;
}
