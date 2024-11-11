package bimo.exception;

/**
 * Represents an Exception for invalid task number inputs.
 */
public class InvalidTaskNumberException extends BimoException {
    /**
     * Instantiates a InvalidTaskNumberException.
     */
    public InvalidTaskNumberException() {
        super("Input must include an integer that represents task number.");
    }
}
