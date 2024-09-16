package darwin.exception;

/**
 * Exception class for illegal task type exceptions.
 */
public class IllegalTaskTypeException extends TaskException {
    public IllegalTaskTypeException(String msg) {
        super(msg);
    }
}
