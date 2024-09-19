package sentinel.exception;

/**
 * The DeadlineException class is a custom exception used for errors related to deadlines in the Sentinel application.
 * It extends the SentinelException class to provide a more specific type of exception for deadline-related issues.
 */
public class DeadlineException extends SentinelException {

    /**
     * Constructs a new DeadlineException with the specified detail message.
     *
     * @param s The detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public DeadlineException(String s) {
        super(s);
    }
}
