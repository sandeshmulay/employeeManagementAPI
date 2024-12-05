package emp.co.dig.infra.exception;

public class DataNotSavedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3426575232639419804L;

	public DataNotSavedException(String message) {
		super(message);
	}

}
