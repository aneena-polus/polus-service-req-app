package com.polus.service.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpOrUpdateDto {

	private Integer employeeId;
	private String firstname;
	private String lastname;
	private String email;
	private String designation;
	private String state;
	private String countryCode;
	private String phoneNumber;
	private String username;
	private String password;
}
