package barney.data.exception;

/**
 * Represents an exception specific to the Barney application. This exception is
 * thrown when an error occurs during the execution of Barney-related
 * operations.
 *
 * @since 1.0
 */
public class BarneyException extends Exception {
    public BarneyException(String message) {
        super(message);
    }
}
