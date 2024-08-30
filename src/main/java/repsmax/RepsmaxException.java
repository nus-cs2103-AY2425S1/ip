/**
 * Represents a custom exception for the Repsmax application.
 * This class extends the standard {@link Exception} class to provide
 * application-specific error handling.
 */
public class RepsmaxException extends Exception {

    /**
     * Constructs a new RepsmaxException with the specified detail message.
     * The detail message is a string that describes the error or exception
     * that occurred.
     *
     * @param message the detail message, which is saved for later retrieval
     *                by the {@link #getMessage()} method.
     */
    public RepsmaxException(String message) {
        super(message);
    }
}
