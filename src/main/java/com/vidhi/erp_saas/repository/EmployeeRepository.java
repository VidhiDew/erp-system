package com.vidhi.erp_saas.repository;

import com.vidhi.erp_saas.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
