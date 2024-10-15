package exception;


/**
 * Exception thrown when a key is not found or empty.
 */
public class KeyNotFoundException extends Exception {
    /**
     * Constructs a new KeyNotFoundException with no detail message.
     */
    public KeyNotFoundException() {
        super();
    }

    /**
     * Constructs a new KeyNotFoundException with the specified detail message.
     *
     * @param message The detail message to be used for this exception.
     */
    public KeyNotFoundException(String message) {
        super(message);
    }
}
