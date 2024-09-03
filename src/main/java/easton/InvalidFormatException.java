package easton;

/**
 * Exception for description is in the wrong format.
 */
public class InvalidFormatException extends Exception {
    /**
     * Construct a new exception with a generated detail message.
     */
    InvalidFormatException() {
        super("This description is in the wrong format!");
    }
}
