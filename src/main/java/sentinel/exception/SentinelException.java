package sentinel.exception;

/**
 * The SentinelException class is a custom exception used within the Sentinel application.
 * It is a general exception that can be thrown to indicate various errors specific to the application.
 */
public class SentinelException extends Exception {

    /**
     * Constructs a new SentinelException with the specified detail message.
     *
     * @param s The detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public SentinelException(String s) {
        super(s);
    }
}
