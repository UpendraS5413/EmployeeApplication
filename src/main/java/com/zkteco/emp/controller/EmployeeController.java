package com.zkteco.emp.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zkteco.emp.dto.ResultDTO;
import com.zkteco.emp.dto.EmployeeDTO;
import com.zkteco.emp.entity.Employee;
import com.zkteco.emp.exception.EmployeeNotFoundException;
import com.zkteco.emp.repository.EmployeeRepository;
import com.zkteco.emp.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@EnableAutoConfiguration
@RestController
@RequestMapping("/api/v1/employee")
@Api(value = "Employee",description = "shows Employee")
public class EmployeeController {
	@Autowired(required = true)
	EmployeeRepository empRepository;

	@Autowired
	private EmployeeService empService;
	
	@ApiResponse(code = 200,message = "success")

	@ApiOperation(value = "returns all Employee data")
	@GetMapping("/listAll")
	public ResultDTO listAll(@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "5") int pageSize) throws EmployeeNotFoundException {
		return empService.getAllEmployee(pageNumber, pageSize);
	}
	
	@ApiOperation(value = "returns employee data by Id")
	@GetMapping("/{id}")
	public ResultDTO fetchEmployeeById(@PathVariable(value = "id") String id) throws EmployeeNotFoundException {
		return empService.fetchEmployeeById(id);
	}

	@ApiOperation(value = "returns saved employee")
	@PostMapping
	public ResultDTO saveEmployee(@Valid @RequestBody EmployeeDTO dto) throws EmployeeNotFoundException {
		return empService.saveEmployee(dto);
	}

	@ApiOperation(value = "returns updated employee")
	@PutMapping("/{id}")
	public ResultDTO updateEmployee(@PathVariable(value = "id") String id, @RequestBody Employee emp)
			throws EmployeeNotFoundException {
		return empService.updateEmployee(id, emp);
	}

	@ApiOperation(value = "returns deleted employee by Id")
	@DeleteMapping
	public ResultDTO deleteEmployeeByName(@RequestParam String id) throws EmployeeNotFoundException {
		return empService.deleteEmployeeById(id);
	}

}
