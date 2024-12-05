package emp.co.dig.system.employee.util;

import org.springframework.stereotype.Component;

import emp.co.dig.system.employee.constant.EmployeeConstant;

@Component
public class AccessTokenUtil {

	public static boolean isValidToken(String accessToken) {
		
		if (accessToken == null || accessToken.isEmpty()) {
			return false;
		}

		return accessToken.equals(EmployeeConstant.ACCESS_TOKEN_VALIDATE_VALUE);
	}
}