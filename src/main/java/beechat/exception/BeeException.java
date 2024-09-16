package beechat.exception;

/**
 * Handle any errors encountered while running Beechat.
 */
public class BeeException extends RuntimeException {

    /**
     * Constructs a new BeeException with the specified detail message.
     *
     * @param message The specified detail message for the exception.
     */
    public BeeException(String message) {
        super(message);
    }
}
