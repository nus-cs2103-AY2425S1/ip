package bob.exception;

/**
 * Catches exceptions relating to the chatBot.
 */
public class BobException extends Exception {
    public BobException(String message) {
        super(message);
    }
}
