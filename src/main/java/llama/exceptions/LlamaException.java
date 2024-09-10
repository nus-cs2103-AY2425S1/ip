package llama.exceptions;

/**
 * Represents runtime exceptions that the program might run into
 */
public class LlamaException extends RuntimeException {
    /**
     * Creates a LlamaException object
     *
     * @param message error message
     */
    public LlamaException(String message) {
        super(message);
    }
}
