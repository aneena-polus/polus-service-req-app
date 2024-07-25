package com.polus.service.app.dto;

import java.util.Date;
import java.util.List;
import com.polus.service.app.entities.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

	private Integer employeeId;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String designation;
	private String state;
	private Country countryCode;
	private String phoneNumber;
	private Date createdDate;
	private List<RoleDto> roles;
}
