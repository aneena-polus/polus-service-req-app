package com.polus.service.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.polus.service.app.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByUsername(String username);

	boolean existsByUsername(String username);

	@Query("SELECT e FROM Employee e JOIN FETCH e.employeeRoles er WHERE er.role.roleId = :roleId")
	List<Employee> findApplicationAdministrator(@Param("roleId") Integer roleId);

	@Query("SELECT e FROM Employee e " + "JOIN e.employeeRoles er1 " + "WHERE er1.role.roleId = 1 AND e.employeeId NOT IN ("
			+ "SELECT pr2.employee.employeeId FROM EmployeeRole pr2 WHERE pr2.role.roleId = 2)")
	List<Employee> findPrincipalInvestigators();
}
