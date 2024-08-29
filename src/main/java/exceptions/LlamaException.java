package exceptions;

/**
 * Represents runtime exceptions that the program might run into
 */
public class LlamaException extends RuntimeException {
    public LlamaException(String message) {
        super(message);
    }
}
