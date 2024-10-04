package bob.exception;

/**
 * The superclass of all custom exceptions in BobBot.
 */
public class BobException extends RuntimeException {
    public BobException(String message) {
        super(message);
    }
}
