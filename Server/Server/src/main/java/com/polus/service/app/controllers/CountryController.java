package com.polus.service.app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.polus.service.app.services.CountryService;

@RestController
@RequestMapping("/api/country")
public class CountryController {

	private Logger logger = LogManager.getLogger(CountryController.class);

	@Autowired
	private CountryService countryService;

	@GetMapping("/getallcountries")
	public ResponseEntity<Object> getAllCountries() {
		logger.info("Request for country list.");
		return ResponseEntity.status(HttpStatus.OK).body(countryService.getAllCountries());
	}
}
