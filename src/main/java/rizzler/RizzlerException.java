package rizzler;

/**
 * Represents Exceptions within the Rizzler chatbot due to violations of user command usage.
 */
public class RizzlerException extends RuntimeException {
    public RizzlerException(String message) {
        super(message);
    }
}
