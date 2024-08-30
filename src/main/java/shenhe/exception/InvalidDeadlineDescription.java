package shenhe.exception;

/**
 * Represents an exception thrown when a deadline description is invalid.
 * <p>
 * The {@code InvalidDeadlineDescription} exception is used to indicate that a command for setting a deadline
 * is missing either the description or the deadline date, or the format of the deadline command is incorrect.
 * The exception provides a specific error message to guide the user in providing a valid deadline description.
 * </p>
 */
public class InvalidDeadlineDescription extends Exception {

    /**
     * Constructs an {@code InvalidDeadlineDescription} with a default error message.
     * <p>
     * The default error message is: "Sorry traveller. For deadlines, you need to have both the description and the deadline separated by '/'".
     * </p>
     */
    public InvalidDeadlineDescription() {
        super("Sorry traveller. For deadlines, you need to have both the description and the deadline separated by '/'");
    }
}
