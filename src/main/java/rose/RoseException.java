package rose;

/**
 * Represents a custom exception used in the application
 * to indicate errors specific to the task management operations.
 *
 * @see java.lang.Exception
 */
public class RoseException extends Exception {

    public RoseException(String message) {
        super(message);
    }
}
