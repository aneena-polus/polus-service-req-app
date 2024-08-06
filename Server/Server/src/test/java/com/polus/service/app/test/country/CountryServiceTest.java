package com.polus.service.app.test.country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.polus.service.app.entities.Country;
import com.polus.service.app.repository.CountryRepository;
import com.polus.service.app.services.CountryService;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

	@Mock
	CountryRepository countryRepository;
	@InjectMocks
	CountryService countryService;

	@Test
	public void testCountriesFound() {
		Country country1 = new Country("IND", "India", "INR", Timestamp.valueOf("2024-07-15 12:12:25"), "Manesh",
				"IND2");
		List<Country> expectedResult = Arrays.asList(country1);
		when(countryRepository.findAll()).thenReturn(expectedResult);
		List<Country> actualResult = countryService.getAllCountries();
		assertEquals(expectedResult, actualResult);
	}
	@Test
	public void testCountriesNotFound() {
		when(countryRepository.findAll()).thenReturn(Collections.emptyList());
		List<Country> actualResult = countryService.getAllCountries();
		assertEquals(0, actualResult.size());
	}
}
