package shoai;

/**
 * Custom exception class for handling errors in the ShoAI application.
 */
public class ShoAIException extends Exception {
    /**
     * Constructs a ShoAIException with the specified error message.
     *
     * @param message The error message.
     */
    public ShoAIException(String message) {
        super(message);
    }
}