package emp.co.dig.system.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emp.co.dig.system.employee.constant.EmployeeConstant;
import emp.co.dig.system.employee.dto.EmployeeInfoResponseDto;
import emp.co.dig.system.employee.dto.EmployeeRequestDto;
import emp.co.dig.system.employee.service.EmployeeService;
import emp.co.dig.system.employee.util.RequestValidation;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<String> createEmployee(@RequestHeader("access-token") String accessToken,
			@RequestBody EmployeeRequestDto employeeRequest) {
		logger.info("Received request to create employee with access token: {}", accessToken);

		try {
			RequestValidation.employeeValidation(employeeRequest);
			employeeService.addEmployee(accessToken, employeeRequest);
			logger.info("Employee created successfully: {}", employeeRequest);
			return new ResponseEntity<>(EmployeeConstant.EMPLOYEE_ADDED, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error occurred while creating employee: {}", e.getMessage(), e);
			throw e;
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeInfoResponseDto> getEmployeeInformation(
			@RequestHeader("access-token") String accessToken, @PathVariable Integer id) {
		logger.info("Received request to get information for employee ID: {} with access token: {}", id, accessToken);

		try {
			RequestValidation.validateEmployeeId(id);
			EmployeeInfoResponseDto response = employeeService.getEmployeeInformation(accessToken, id);
			logger.info("Retrieved employee information for ID: {}", id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while retrieving employee information for ID: {}: {}", id, e.getMessage(), e);
			throw e;
		}
	}
	
}
