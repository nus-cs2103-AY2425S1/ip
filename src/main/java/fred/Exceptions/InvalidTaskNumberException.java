package fred.Exceptions;

/**
 * The InvalidTaskNumberException is thrown when a user provides
 * an invalid task number while trying to perform an operation on a task
 * in the Fred application.
 */
public class InvalidTaskNumberException extends FredException {

    /**
     * Constructs an InvalidTaskNumberException with a default error message
     * indicating that the provided task number is invalid.
     */
    public InvalidTaskNumberException() {
        super("OOPS!!! That's not a valid task number!");
    }
}
