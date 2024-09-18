package sentinel.exception;

import sentinel.utils.SentinelString;

/**
 * The EventException class is a custom exception used for errors related to events in the Sentinel application.
 * It extends the SentinelException class to provide a more specific type of exception for event-related issues.
 */
public class ExecutionException extends SentinelException {
    /**
     * Constructs a new ExecutionException with the specified detail message.
     *
     * @param s The detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public ExecutionException() {
        super(SentinelString.stringExecutionErrorMessage());
    }
}
