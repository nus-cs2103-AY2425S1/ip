package darwin.exception;

/**
 * Exception class for task argument exceptions.
 */
public class IllegalTaskArgumentException extends TaskException {
    public IllegalTaskArgumentException(String msg) {
        super(msg);
    }
}
