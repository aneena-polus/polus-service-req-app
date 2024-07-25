package com.polus.service.app.services;

import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.polus.service.app.entities.Country;
import com.polus.service.app.repository.CountryRepository;

@Service
public class CountryService {

	private Logger logger = LogManager.getLogger(CountryService.class);

	@Autowired
	CountryRepository countryRepository;

	public List<Country> getAllCountries() {
		try {
			logger.info("Service method to fetch conuntry details");
			List<Country> countries = countryRepository.findAll();
			if(countries.isEmpty()) {
				return Collections.emptyList();
			}
			return countries;
		} catch (DataAccessException e) {
			logger.error("Error in fetching countries: {}", e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
}
