package com.polus.service.app.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

	private Integer employeeId;
	private String firstName;
	private String lastName;
	private List<RoleDto> roles;
}
