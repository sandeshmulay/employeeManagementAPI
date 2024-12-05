package emp.co.dig.infra.exception;
public class AccessTokenException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4011212425605236951L;

	public AccessTokenException(String message) {
        super(message);
    }
}