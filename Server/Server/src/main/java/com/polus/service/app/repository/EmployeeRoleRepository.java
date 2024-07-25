package com.polus.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.polus.service.app.entities.EmployeeRole;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM EMPLOYEE_ROLE WHERE EMPLOYEE_ID = :employeeId AND ROLE_ID = :roleId", nativeQuery = true)
	void deleteByEmployeeAndRole(@Param("employeeId") Integer employeeId, @Param("roleId") Integer roleId);
}
