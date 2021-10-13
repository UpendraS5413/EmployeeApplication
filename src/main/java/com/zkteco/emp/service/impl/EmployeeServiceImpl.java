package com.zkteco.emp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.lang.String;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.zkteco.emp.dto.ResultDTO;
import com.zkteco.emp.counts.ErrorCounts;
import com.zkteco.emp.counts.SuccessCounts;
import com.zkteco.emp.dto.EmployeeDTO;
import com.zkteco.emp.entity.Employee;
import com.zkteco.emp.exception.EmployeeNotFoundException;
import com.zkteco.emp.repository.EmployeeRepository;
import com.zkteco.emp.service.EmployeeService;

import io.swagger.annotations.Api;

@Component
@Api
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository empRepository;
	@Autowired
	EmployeeService empService;

	public EmployeeDTO entityToDto(Employee emp) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(emp.getEmpId());
		dto.setFirstName(emp.getFirstName());
		dto.setLastName(emp.getLastName());
		dto.setEmpCode(emp.getEmpCode());
		dto.setEmailId(emp.getEmailId());
		dto.setDesignation(emp.getDesignation());
		dto.setDob(emp.getDob());
		dto.setMobile(emp.getMobile());
		return dto;
	}

	public List<EmployeeDTO> entityToDto(List<Employee> emp) {
		return emp.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}

	public Employee dtoToEntity(EmployeeDTO dto) {
		Employee emp = new Employee();
		emp.setEmpId(dto.getId());
		emp.setFirstName(dto.getFirstName());
		emp.setLastName(dto.getLastName());
		emp.setEmpCode(dto.getEmpCode());
		emp.setEmailId(dto.getEmailId());
		emp.setDesignation(dto.getDesignation());
		emp.setDob(dto.getDob());
		emp.setMobile(dto.getMobile());
		return emp;
	}

	public List<Employee> dtoToEntity(List<EmployeeDTO> dto) {
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}

	@Override
	public ResultDTO getAllEmployee(int pageNumber, int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Employee> employee = empRepository.findAll(page);
		List<Employee> emp = new ArrayList<Employee>();
		for (Employee e : employee) {
			emp.add(e);
		}
		ResultDTO res = new ResultDTO();
		res.setCode("Emp001");
		res.setMessage("successfully fetched");
		res.setData(emp);
		return res;
	}

	@Override
	public ResultDTO fetchEmployeeById(String id) throws EmployeeNotFoundException {
		Optional<Employee> orElse = empRepository.findById(id);
		if (!orElse.isPresent()) {
			throw new EmployeeNotFoundException("Employee Not Available");
		}
		Employee emp = orElse.get();
		EmployeeDTO res = empService.entityToDto(emp);
		ResultDTO empDTO = new ResultDTO();
		empDTO.setCode("Emp002");
		empDTO.setMessage("Successfully fetched by Id");
		empDTO.setData(res);
		return empDTO;
	}

	@Override
	public ResultDTO saveEmployee(EmployeeDTO dto) throws EmployeeNotFoundException {
		Employee emp1 = empService.dtoToEntity(dto);
		emp1.setCreateDate(new Date());
		emp1 = empRepository.save(emp1);
		EmployeeDTO res = empService.entityToDto(emp1);
		ResultDTO empDTO = new ResultDTO();
		empDTO.setCode("Emp001");
		empDTO.setMessage("Employee Successfully Created");
		empDTO.setData(res);
		return empDTO;
	}

	@Override
	public ResultDTO updateEmployee(String id, Employee emp1) throws EmployeeNotFoundException {

		Optional<Employee> employee = empRepository.findById(id);
		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee Not Available");
		}
		Employee emp = employee.get();

		if (Objects.nonNull(emp1.getFirstName()) && !"".equalsIgnoreCase(emp1.getFirstName())) {
			emp.setFirstName(emp1.getFirstName());
		}
		if (Objects.nonNull(emp1.getLastName()) && !"".equalsIgnoreCase(emp1.getLastName())) {
			emp.setLastName(emp1.getLastName());
		}
		if (Objects.nonNull(emp1.getEmpCode()) && !"".equalsIgnoreCase(emp1.getEmpCode())) {
			emp.setEmpCode(emp1.getEmpCode());
		}
		if (Objects.nonNull(emp1.getDesignation()) && !"".equalsIgnoreCase(emp1.getDesignation())) {
			emp.setDesignation(emp1.getDesignation());
		}
		if (Objects.nonNull(emp1.getEmailId()) && !"".equalsIgnoreCase(emp1.getEmailId())) {
			emp.setEmailId(emp1.getEmailId());
		}
		if (Objects.nonNull(emp1.getDob()) && !"".equalsIgnoreCase(emp1.getDob())) {
			emp.setDob(emp1.getDob());
		}
		if (Objects.nonNull(emp1.getMobile()) && !"".equalsIgnoreCase(emp1.getMobile())) {
			emp.setMobile(emp1.getMobile());
		}
		emp.setUpdateDate(new Date());
		Employee emp2 = empRepository.save(emp);
		EmployeeDTO res = empService.entityToDto(emp2);
		ResultDTO empDTO = new ResultDTO();
		empDTO.setCode("Emp003");
		empDTO.setMessage("Successfully updated by Id");
		empDTO.setData(res);
		return empDTO;
	}

	@Override
	public ResultDTO deleteEmployeeById(String ids) throws EmployeeNotFoundException {
		String[] strArr = null;
		strArr = ids.split(",");
		int successCount = 0;
		int errorCount = 0;
		ResultDTO res = new ResultDTO();
		List<SuccessCounts> ct = new ArrayList<SuccessCounts>();
		SuccessCounts count = new SuccessCounts();
		ErrorCounts count1 = new ErrorCounts();
		ArrayList<String> s = new ArrayList<String>();
		List<ResultDTO> res2 = new ArrayList<ResultDTO>();
		for (String st : strArr) {
			if (empRepository.existsById(st)) {
				empRepository.deleteById(st);
				s.add(st);
				successCount++;
				count.setSuccessCount(successCount);
				count.setSuccess(s);
				ct.add(count);
			} else {
				errorCount++;
				count1.setErrorCount(errorCount);
				ResultDTO res1 = new ResultDTO();
				res1.setCode("Emp005");
				res1.setMessage("id not found");
				res1.setData("{ id : " + st + " }");
				res2.add(res1);
				count1.setError(res2);
			}
		}
		List<Object> obj = new ArrayList<Object>();
		obj.add(count);
		obj.add(count1);
		res.setCode("Emp005");
		res.setMessage("One or more objects are not processed");
		res.setData(obj);
		return res;
	}

}