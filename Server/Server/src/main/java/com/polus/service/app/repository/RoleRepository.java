package com.polus.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polus.service.app.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
