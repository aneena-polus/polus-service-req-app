package com.polus.service.app.test.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.polus.service.app.dto.LoginRequest;
import com.polus.service.app.dto.LoginResponse;
import com.polus.service.app.dto.RoleDto;
import com.polus.service.app.entities.Country;
import com.polus.service.app.entities.Employee;
import com.polus.service.app.entities.EmployeeRole;
import com.polus.service.app.entities.Role;
import com.polus.service.app.repository.EmployeeRepository;
import com.polus.service.app.services.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Mock
	EmployeeRepository employeeRepository;
	@InjectMocks
	EmployeeService employeeService;

	@Test
	public void testLogin() {
		//Expected Result
		RoleDto role1 = new RoleDto(1, "USER", "Description");
		List<RoleDto> roles = Arrays.asList(role1);
		LoginResponse expectedResult = new LoginResponse(1, "manesh", "Manesh", "M S", "mail", "Designation", "Kerala",
				"IND", "656565656", Timestamp.valueOf("2024-07-15 11:36:58"), "password", roles);

		LoginRequest request = new LoginRequest("manesh", "password");
		Country country = new Country();
		country.setCountryCode("IND");
		Employee employee1 = new Employee(1, "Manesh", "M S", "mail", "Designation", "Kerala", country, "656565656",
				"manesh", "password", Timestamp.valueOf("2024-07-15 11:36:58"), "Manesh M S", null);
		Role userRole = new Role();
		userRole.setRoleId(1);
		userRole.setRoleName("USER");
		userRole.setRoleDescription("Description");
		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setId(1);
		employeeRole.setEmployee(employee1);
		employeeRole.setRole(userRole);
		Set<EmployeeRole> EmployeeRoleSet = Sets.set(employeeRole);
		Employee employee = new Employee(1, "Manesh", "M S", "mail", "Designation", "Kerala", country, "656565656",
				"manesh", "password", Timestamp.valueOf("2024-07-15 11:36:58"), "Manesh M S", EmployeeRoleSet);
		
		when(employeeRepository.findByUsername(request.getUsername())).thenReturn(employee);

		LoginResponse actualResult = employeeService.login(request);
		assertEquals(expectedResult, actualResult);
	}
}
