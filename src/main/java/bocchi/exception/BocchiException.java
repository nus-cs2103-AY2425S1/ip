package bocchi.exception;

/**
 * This class is used to throw exceptions in the Bocchi application.
 */
public class BocchiException extends Exception {
    /**
     * The constructor.
     *
     * @param message Error message.
     */
    public BocchiException(String message) {
        super(message);
    }
}
