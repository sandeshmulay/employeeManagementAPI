package emp.co.dig.infra.exception;

public class EmployeeMandatoryFieldException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5044152473928233150L;

	public EmployeeMandatoryFieldException(String message) {

		super(message);
	}
}
