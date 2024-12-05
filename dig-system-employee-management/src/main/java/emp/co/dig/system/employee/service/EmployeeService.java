package emp.co.dig.system.employee.service;

import emp.co.dig.system.employee.dto.EmployeeInfoResponseDto;
import emp.co.dig.system.employee.dto.EmployeeRequestDto;

public interface EmployeeService {

	void addEmployee(String accessToken, EmployeeRequestDto employeeRequest);

	EmployeeInfoResponseDto getEmployeeInformation(String accessToken, Integer id);

}
