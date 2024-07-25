package com.polus.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polus.service.app.entities.Country;

public interface CountryRepository extends JpaRepository<Country, String> {	
}
