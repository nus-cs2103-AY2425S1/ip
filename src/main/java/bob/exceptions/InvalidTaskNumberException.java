package bob.exceptions;

/**
 * InvalidTaskNumberException is thrown when the user inputs an invalid Task Number
 */
public class InvalidTaskNumberException extends Exception {

    /**
     * Constructor for InvalidTaskNumberException
     */
    public InvalidTaskNumberException() {
        super("OOPS!! An invalid task number has been entered");
    }
}
