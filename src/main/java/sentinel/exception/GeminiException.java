package sentinel.exception;

import sentinel.utils.SentinelString;

/**
 * The GeminiException class is a custom exception used for errors related to Gemini in the Sentinel application.
 * It extends the SentinelException class to provide a more specific type of exception for Gemini-related issues.
 */
public class GeminiException extends SentinelException {
    /**
     * Constructs a new SentinelException with the specified detail message.
     */
    public GeminiException() {
        super(SentinelString.stringGeminiErrorMessage());
    }
}
