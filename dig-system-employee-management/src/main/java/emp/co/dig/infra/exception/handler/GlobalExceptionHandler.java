package emp.co.dig.infra.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import emp.co.dig.infra.exception.AccessTokenException;
import emp.co.dig.infra.exception.DataNotFoundException;
import emp.co.dig.infra.exception.DataNotSavedException;
import emp.co.dig.infra.exception.EmployeeInvalidDataException;
import emp.co.dig.infra.exception.EmployeeMandatoryFieldException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccessTokenException.class)
	public ResponseEntity<String> handleAccessTokenException(AccessTokenException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(EmployeeMandatoryFieldException.class)
	public ResponseEntity<String> employeeMandatoryFieldException(EmployeeMandatoryFieldException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmployeeInvalidDataException.class)
	public ResponseEntity<String> employeeInvalidDataException(EmployeeInvalidDataException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<String> dataNotFoundException(DataNotFoundException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DataNotSavedException.class)
	public ResponseEntity<String> dataNotSavedException(DataNotSavedException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}