package com.polus.service.app.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginResponse {

	private Integer employeeId;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String designation;
	private String state;
	private String countryCode;
	private String phoneNumber;
	private Date createdDate;
	private String password;
	private List<RoleDto> roles;
}
