package com.polus.service.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polus.service.app.dto.LoginRequest;
import com.polus.service.app.dto.LoginResponse;
import com.polus.service.app.dto.SignUpOrUpdateDto;
import com.polus.service.app.exceptions.UsernameAlreadyExistsException;
import com.polus.service.app.repository.EmployeeRepository;
import com.polus.service.app.services.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class EmployeeController {

	private Logger logger = LogManager.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService authService;

	@Autowired
	EmployeeRepository employee;

	@PostMapping("/signupOredit")
	public ResponseEntity<Object> signUpOrEdit(@RequestBody SignUpOrUpdateDto signUpRequest) {
		logger.info("Request for a employee sign-up");
		return authService.signUpOrEdit(signUpRequest);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
		logger.info("Request for employee login.");
		LoginResponse loginResponse = authService.login(loginRequest);
		if (loginResponse == null) {
			logger.info("Username or password is incorrect.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password is incorrect.");
		}
		return ResponseEntity.ok(loginResponse);
	}

	@GetMapping("setrole/{employeeId}/{roleId}")
	public ResponseEntity<Map<String, String>> assignRole(@PathVariable int employeeId, @PathVariable int roleId) {
		logger.info("Request for assigning roles to an employee");
		Map<String, String> message = new HashMap<>();
		try {
			if (authService.assignRole(employeeId, roleId)) {
				logger.info("Role assigned successfully.");
				message.put("Message", "Role assigned successfully");
				return ResponseEntity.status(HttpStatus.OK).body(message);
			} else {
				logger.info("Role assigned failed.");
				message.put("Message", "Role assigned failed.");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
			}
		} catch (Exception e) {
			message.put("Message", e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
	}

	@GetMapping("/employeelist/{roleId}")
	public ResponseEntity<Object> getAllEmployees(@PathVariable int roleId) {
		logger.info("Request to fetch all employee details");
		return ResponseEntity.status(HttpStatus.OK).body(authService.getAllEmployeesWithRole(roleId));
	}

	@DeleteMapping("/deleterole/{adminId}/{employeeId}/{roleId}")
	public ResponseEntity<Map<String, String>> revokeRole(@PathVariable Integer adminId,
			@PathVariable Integer employeeId, @PathVariable Integer roleId) {
		return ResponseEntity.status(HttpStatus.OK).body(authService.revokeRole(adminId, employeeId, roleId));
	}

	@ExceptionHandler(UsernameAlreadyExistsException.class)
	public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	//Learning purpose
	@GetMapping("/get")
	public String getAll() {
		String name = "Manesh";
		return name;
	}
}
