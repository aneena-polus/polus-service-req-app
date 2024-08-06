package com.polus.service.app.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.polus.service.app.Constants;
import com.polus.service.app.dto.EmployeeDto;
import com.polus.service.app.dto.LoginRequest;
import com.polus.service.app.dto.LoginResponse;
import com.polus.service.app.dto.RoleDto;
import com.polus.service.app.dto.SignUpOrUpdateDto;
import com.polus.service.app.entities.Country;
import com.polus.service.app.entities.Employee;
import com.polus.service.app.entities.EmployeeRole;
import com.polus.service.app.entities.Role;
import com.polus.service.app.exceptions.UsernameAlreadyExistsException;
import com.polus.service.app.repository.CountryRepository;
import com.polus.service.app.repository.EmployeeRepository;
import com.polus.service.app.repository.EmployeeRoleRepository;
import com.polus.service.app.repository.RoleRepository;

@Service
public class EmployeeService {

	private Logger logger = LogManager.getLogger(EmployeeService.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	EmployeeRoleRepository employeeRoleRepository;

	@Autowired
	CountryRepository countryRespository;

	public ResponseEntity<Object> signUpOrEdit(SignUpOrUpdateDto signUpOrUpdate) {
		Map<String, String> message = new HashMap<>();
		try {
			Employee employee = new Employee();
			Optional<Country> country = countryRespository.findById(signUpOrUpdate.getCountryCode());
			if (signUpOrUpdate.getEmployeeId() == null) {
				if (employeeRepository.existsByUsername(signUpOrUpdate.getUsername())) {
					throw new UsernameAlreadyExistsException("Username already exists");
				}
				employee.setCreatedDate(Timestamp.from(Instant.now()));
				saveOrUpdateEmployee(employee, signUpOrUpdate, country);
				employeeRepository.save(employee);
				assignRole(employee.getEmployeeId(), Constants.PRINCIPAL_INVESTIGATOR_ID);
				logger.info("User signup successfully");
				message.put("Message", "User signup successfully");
				message.put("Username", signUpOrUpdate.getUsername());
				message.put("Password", signUpOrUpdate.getPassword());
				return ResponseEntity.ok(message);
			} else {
				saveOrUpdateEmployee(employee, signUpOrUpdate, country);
				employee.setEmployeeId(signUpOrUpdate.getEmployeeId());
				employeeRepository.save(employee);
				logger.info("User edit successfully");
				message.put("Message", "User edit successfully");
				LoginResponse editResponse = new LoginResponse();
				editResponse.setEmployeeId(employee.getEmployeeId());
				editResponse.setUsername(employee.getUsername());
				editResponse.setFirstname(employee.getFirstname());
				editResponse.setLastname(employee.getLastname());
				editResponse.setEmail(employee.getEmail());
				editResponse.setDesignation(employee.getDesignation());
				editResponse.setState(employee.getState());
				editResponse.setCountryCode(employee.getCountryCode().getCountryCode());
				editResponse.setPhoneNumber(employee.getPhoneNumber());
				return ResponseEntity.ok(editResponse);
			}
		} catch (UsernameAlreadyExistsException e) {
			logger.error("Username already exist: {}", e.getMessage());
			message.put("Message", "Username already exists");
			return ResponseEntity.ok(message);
		} catch (Exception e) {
			logger.error("Error in sign-up: {}", e.getMessage());
			message.put("Message", "Failed");
			return ResponseEntity.ok(message);
		}
	}

	public LoginResponse login(LoginRequest loginRequest) {
		try {
			Employee employee = employeeRepository.findByUsername(loginRequest.getUsername());
			if (employee != null && employee.getPassword().equals(loginRequest.getPassword())) {
				LoginResponse loginResponse = new LoginResponse();
				loginResponse.setEmployeeId(employee.getEmployeeId());
				loginResponse.setUsername(employee.getUsername());
				loginResponse.setPassword(employee.getPassword());
				loginResponse.setFirstname(employee.getFirstname());
				loginResponse.setLastname(employee.getLastname());
				loginResponse.setEmail(employee.getEmail());
				loginResponse.setDesignation(employee.getDesignation());
				loginResponse.setState(employee.getState());
				loginResponse.setCountryCode(employee.getCountryCode().getCountryCode());
				loginResponse.setPhoneNumber(employee.getPhoneNumber());
				loginResponse.setCreatedDate(employee.getCreatedDate());
				loginResponse.setRoles(getRole(employee));
				return loginResponse;
			}
		} catch (DataAccessException e) {
			logger.error("Error in login: {}", e.getMessage());
			throw new RuntimeException(e);
		}
		return null;
	}

	public boolean assignRole(int employeeId, int roleId) {
		try {
			Employee employee = employeeRepository.findById(employeeId)
					.orElseThrow(() -> new RuntimeException("Employee not found"));
			Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
			EmployeeRole employeeRole = new EmployeeRole();
			employeeRole.setEmployee(employee);
			employeeRole.setRole(role);
			employeeRoleRepository.save(employeeRole);
			return true;
		} catch (Exception e) {
			logger.error("Error in role assigning: {}", e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public List<EmployeeDto> getAllEmployeesWithRole(int roleId) {
		try {
			if (roleId == Constants.APPLICATION_ADMINISTRATOR_ID) {
				List<Employee> employeeList = employeeRepository.findApplicationAdministrator(roleId);
				if (employeeList.isEmpty()) {
					return Collections.emptyList();
				}
				return getEmployeeListByRole(employeeList);
			}
			if (roleId == Constants.PRINCIPAL_INVESTIGATOR_ID) {
				List<Employee> employeeList = employeeRepository.findPrincipalInvestigators();
				if (employeeList.isEmpty()) {
					return Collections.emptyList();
				}
				return getEmployeeListByRole(employeeList);
			}
		} catch (DataAccessException e) {
			logger.error("Error in fetching all employees: ", e.getMessage());
			throw new RuntimeException(e);
		}
		return null;
	}

	public Map<String, String> revokeRole(Integer adminId, Integer employeeId, Integer roleId) {
		Map<String, String> message = new HashMap<>();
		try {
			if (employeeRepository.existsById(employeeId) && employeeRepository.existsById(adminId)) {
				employeeRoleRepository.deleteByEmployeeAndRole(employeeId, roleId);
				message.put("Message", "Role deleted successfully");
			} else
				message.put("Message", "Employee not found");
			return message;
		} catch (Exception e) {
			logger.info("Error in: ", e.getMessage());
			message.put("Message", "Error in revoke roles");
			return message;
		}
	}

	public List<EmployeeDto> getEmployeeListByRole(List<Employee> employeeList) {
		List<EmployeeDto> employeeDto = new ArrayList<>();
		for (Employee employee : employeeList) {
			EmployeeDto list = new EmployeeDto();
			list.setEmployeeId(employee.getEmployeeId());
			list.setFullname(employee.getFullname());
			list.setDesignation(employee.getDesignation());
			list.setRoles(getRole(employee));
			employeeDto.add(list);
		}
		return employeeDto;
	}

	public List<RoleDto> getRole(Employee employee) {
		List<RoleDto> roles = employee.getEmployeeRoles().stream().map(employeeRole -> {
			RoleDto roleDto = new RoleDto();
			roleDto.setRoleId(employeeRole.getRole().getRoleId());
			roleDto.setRoleName(employeeRole.getRole().getRoleName());
			roleDto.setRoleDescription(employeeRole.getRole().getRoleDescription());
			return roleDto;
		}).collect(Collectors.toList());
		return roles;
	}

	public Employee saveOrUpdateEmployee(Employee employee, SignUpOrUpdateDto signUpOrUpdate, Optional<Country> country) {
		employee.setFirstname(signUpOrUpdate.getFirstname());
		employee.setLastname(signUpOrUpdate.getLastname());
		employee.setFullname(signUpOrUpdate.getFirstname() + " " + signUpOrUpdate.getLastname());
		employee.setEmail(signUpOrUpdate.getEmail());
		employee.setDesignation(signUpOrUpdate.getDesignation());
		employee.setState(signUpOrUpdate.getState());
		employee.setCountryCode(country.get());
		employee.setPhoneNumber(signUpOrUpdate.getPhoneNumber());
		employee.setUsername(signUpOrUpdate.getUsername());
		employee.setPassword(signUpOrUpdate.getPassword());
		return employee;
	}
}
