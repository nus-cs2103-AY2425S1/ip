package GPT;
/**
 * Represents a custom exception used in the GPT application.
 * This exception is thrown when an error specific to the GPT application occurs.
 */
public class GPTException extends Exception {
    /**
     * Constructs a new GPTException with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public GPTException(String message) {
        super(message);
    }
}
