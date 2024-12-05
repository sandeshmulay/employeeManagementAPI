package emp.co.dig.infra.exception;

public class EmployeeInvalidDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3293945450121467453L;

	public EmployeeInvalidDataException(String message) {
		super(message);
	}

}
