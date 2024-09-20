package bobby.exceptions;

/**
 * The {@code InvalidTaskNumberException} class represents a specific type of {@code BobbyException}
 * that is thrown when the user provides an invalid task number that is out of the valid range.
 */
public class InvalidTaskNumberException extends BobbyException {

    /**
     * Constructs a new {@code InvalidTaskNumberException} with a default detail message
     * indicating that the provided task number is invalid.
     */
    public InvalidTaskNumberException() {
        super("The task number provided is invalid.");
    }
}
