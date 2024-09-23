package exception;

/**
 * Represents a general exception for the Dash application.
 */
public class DashException extends Exception {

    /**
     * Constructs a DashException with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public DashException(String message) {
        super(message);
    }
}
