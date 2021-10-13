package com.zkteco.emp.service;

import java.util.List;
import com.zkteco.emp.dto.ResultDTO;
import com.zkteco.emp.dto.EmployeeDTO;
import com.zkteco.emp.entity.Employee;
import com.zkteco.emp.exception.EmployeeNotFoundException;

public interface EmployeeService {

	public EmployeeDTO entityToDto(Employee emp);

	public List<EmployeeDTO> entityToDto(List<Employee> emp);

	public Employee dtoToEntity(EmployeeDTO dto);

	public List<Employee> dtoToEntity(List<EmployeeDTO> dto);

	public ResultDTO getAllEmployee(int pageNumber, int pageSize);

	public ResultDTO fetchEmployeeById(String id) throws EmployeeNotFoundException;

	public ResultDTO saveEmployee(EmployeeDTO dto) throws EmployeeNotFoundException;

	public ResultDTO updateEmployee(String id, Employee emp) throws EmployeeNotFoundException;

	public ResultDTO deleteEmployeeById(String ids) throws EmployeeNotFoundException;

}
