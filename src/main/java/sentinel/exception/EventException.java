package sentinel.exception;

/**
 * The EventException class is a custom exception used for errors related to events in the Sentinel application.
 * It extends the SentinelException class to provide a more specific type of exception for event-related issues.
 */
public class EventException extends SentinelException {

    /**
     * Constructs a new EventException with the specified detail message.
     *
     * @param s The detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public EventException(String s) {
        super(s);
    }
}
