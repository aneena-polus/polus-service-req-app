package com.polus.service.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.polus.service.app.controllers.EmployeeController;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc
public class Controller {

	@Autowired
	MockMvc mockMvc;

	@Test
	void findAllEmployees() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/get"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
