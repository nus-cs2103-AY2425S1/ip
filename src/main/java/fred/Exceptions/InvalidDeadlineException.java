package fred.Exceptions;

/**
 * The InvalidDeadlineException is thrown when a user provides an invalid format
 * for creating a deadline task in the Fred application.
 */
public class InvalidDeadlineException extends FredException {

    /**
     * Constructs an InvalidDeadlineException with a default error message
     * that guides the user to the correct format for a deadline task.
     */
    public InvalidDeadlineException() {
        super("OOPS!!! Please use this format: deadline return book /by 2000-01-01 00:00");
    }
}
