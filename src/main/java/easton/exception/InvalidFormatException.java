package easton.exception;

/**
 * Exception for description is in the wrong format.
 */
public class InvalidFormatException extends Exception {
    /**
     * Constructs a new exception with a generated detail message.
     */
    public InvalidFormatException() {
        super("This description is in the wrong format!");
    }
}
