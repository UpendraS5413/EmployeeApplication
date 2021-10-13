package com.zkteco.emp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.zkteco.emp.entity.ErrorMessage;

@ControllerAdvice
@ResponseStatus
public class RestRepositoryEntityExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorMessage> EmployeeNotFoundException(EmployeeNotFoundException exception,
			WebRequest request) {

		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

	}

}