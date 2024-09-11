package bob.data;

/**
 * Exception thrown by the Bob application.
 */
public class BobException extends Exception {
    /**
     * Returns an exception with a message.
     *
     * @param message The message for the exception.
     * @return The exception.
     */
    public BobException(String message) {
        super(message);
    }
}
