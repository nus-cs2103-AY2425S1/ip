package sentinel.exception;

/**
 * The GeminiException class is a custom exception used for errors related to Gemini in the Sentinel application.
 * It extends the SentinelException class to provide a more specific type of exception for Gemini-related issues.
 */
public class GeminiException extends SentinelException {
    /**
     * Constructs a new SentinelException with the specified detail message.
     *
     * @param s The detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public GeminiException(String s) {
        super(s);
    }
}
