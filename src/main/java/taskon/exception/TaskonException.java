package taskon.exception;

/**
 * Represents an exception specific to the Taskon application.
 *
 * `TaskonException` is used to indicate that an error has occurred within
 * the Taskon application, typically due to invalid user input or other
 * application-specific issues.
 */
public class TaskonException extends Exception {

    /**
     * Constructs a new TaskonException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the `getMessage()` method).
     */
    public TaskonException(String message) {
        super(message);
    }
}
