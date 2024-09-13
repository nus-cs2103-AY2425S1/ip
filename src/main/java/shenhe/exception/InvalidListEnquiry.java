package shenhe.exception;

/**
 * Represents an exception that is thrown when an invalid command for listing tasks is encountered.
 * <p>
 * The {@code InvalidListEnquiry} exception indicates that the user has entered an incorrect command
 * when attempting to list the tasks. This exception provides a specific error message to remind the
 * user that the correct command to view the current list of tasks is simply 'list'.
 * </p>
 */
public class InvalidListEnquiry extends Exception {

    /**
     * Constructs an {@code InvalidListEnquiry} exception with a default error message.
     * <p>
     * The default error message is: "Dear traveller. If you want to check out the current list,
     * please type in 'list' only."
     * </p>
     */
    public InvalidListEnquiry() {
        super("Dear traveller. If you want to check out the current list, please type in 'list' only.");
    }
}
