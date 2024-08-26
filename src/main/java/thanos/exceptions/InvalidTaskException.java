package thanos.exceptions;

/**
 * Exception thrown to indicate that a task is invalid.
 * <p>
 * The {@code InvalidTaskException} class extends {@code Exception} and is used to signal issues with tasks
 * that are not formatted correctly or contain errors. It provides a custom error message to indicate
 * the nature of the problem.
 * </p>
 */
public class InvalidTaskException extends Exception {
    /**
     * Constructs an {@code InvalidTaskException} with the specified detail message.
     *
     * @param message the detail message to be included in the exception. This message provides information
     *                about why the task is considered invalid and can be used for debugging or user feedback.
     */
    public InvalidTaskException(String message) {
        super(message);
    }
}
