package com.polus.service.app.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

	private Integer employeeId;
	private String fullname;
	private String designation;
	private List<RoleDto> roles;
}
