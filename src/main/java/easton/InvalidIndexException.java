package easton;

/**
 * Exception for an invalid index is given.
 */
public class InvalidIndexException extends Exception {
    /**
     * Construct a new exception with a generated detail message.
     */
    InvalidIndexException(String input) {
        super(input + " is an invalid index!");
    }
}
