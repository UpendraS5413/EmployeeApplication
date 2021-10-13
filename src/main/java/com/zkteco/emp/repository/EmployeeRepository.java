package com.zkteco.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zkteco.emp.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

//	@Query(value = "Select t from Employee t where t.firstName like ?1 OR t.mobile like ?1 OR t.lastName like ?1 OR t.empcode like ?1 OR t.emailId like ?1 OR t.designation like ?1")
//	Page<Employee> employeeContaining(String search, Pageable paging) throws EmployeeNotFoundException;

}
