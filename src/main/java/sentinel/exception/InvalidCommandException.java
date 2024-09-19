package sentinel.exception;

import sentinel.utils.SentinelString;

/**
 * The EventException class is a custom exception used for errors related to events in the Sentinel application.
 * It extends the SentinelException class to provide a more specific type of exception for event-related issues.
 */
public class InvalidCommandException extends SentinelException {
    /**
     * Constructs a new InvalidCommandException with the specified detail message.
     */
    public InvalidCommandException() {
        super(SentinelString.stringUnrecognisedCommand());
    }
}
