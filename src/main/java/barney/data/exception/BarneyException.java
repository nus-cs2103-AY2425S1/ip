package barney.data.exception;

/**
 * Represents an exception specific to the Barney application. This exception is
 * thrown when an error occurs during the execution of Barney-related
 * operations.
 */
public class BarneyException extends Exception {

    /**
     * Constructs a new BarneyException with the specified detail message.
     *
     * @param message The detail message.
     */
    public BarneyException(String message) {
        super(message);
    }
}
