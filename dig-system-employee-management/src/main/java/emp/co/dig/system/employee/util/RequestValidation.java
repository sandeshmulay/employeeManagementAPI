package emp.co.dig.system.employee.util;

import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import emp.co.dig.infra.exception.EmployeeInvalidDataException;
import emp.co.dig.infra.exception.EmployeeMandatoryFieldException;
import emp.co.dig.system.employee.constant.EmployeeConstant;
import emp.co.dig.system.employee.dto.EmployeeRequestDto;

public class RequestValidation {

	public static void employeeValidation(EmployeeRequestDto employeeRequest) {

		validateMandatorySpecification(employeeRequest);
		isValidEmail(employeeRequest.getEmail());

	}

	private static void validateMandatorySpecification(EmployeeRequestDto employeeRequest) {

		if (StringUtils.isEmpty(employeeRequest.getName())) {
			throw new EmployeeMandatoryFieldException(EmployeeConstant.INVALID_EMP_NAME);
		} else if (StringUtils.isEmpty(employeeRequest.getEmail())) {
			throw new EmployeeMandatoryFieldException(EmployeeConstant.INVALID_EMP_EMAIL);
		}

	}

	private static void isValidEmail(String email) {
		Pattern pattern = Pattern.compile(EmployeeConstant.EMAIL_REGEX);
		if (!pattern.matcher(email).matches()) {
			throw new EmployeeInvalidDataException(EmployeeConstant.INVALID_EMP_EMAIL_fORMAT);
		}

	}

	public static void validateEmployeeId(Integer id) {

		if (Objects.isNull(id) || id == 0) {
			throw new EmployeeInvalidDataException(EmployeeConstant.INVALID_EMPLOYEE_ID);
		}

	}

}
