package exception;

/**
 * Represents an exception specific to the Diego application.
 */
public class DiegoException extends Exception {
    /**
     * Constructs a new DiegoException with a specific error message.
     *
     * @param message Error message associated with the exception.
     */
    public DiegoException(String message) {
        super(message);
    }
}
