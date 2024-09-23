package easton.exception;

/**
 * Exception for an invalid index is given.
 */
public class InvalidIndexException extends Exception {
    /**
     * Constructs a new exception with a specified detail message.
     *
     * @param input Input that was given.
     */
    public InvalidIndexException(String input) {
        super(input + " is an invalid index!");
    }
}
