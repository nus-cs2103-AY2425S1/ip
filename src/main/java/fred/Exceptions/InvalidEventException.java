package fred.Exceptions;

/**
 * The InvalidEventException is thrown when a user provides an invalid format
 * for creating an event task in the Fred application.
 */
public class InvalidEventException extends FredException {

    /**
     * Constructs an InvalidEventException with a default error message
     * that guides the user to the correct format for an event task.
     */
    public InvalidEventException() {
        super("OOPS!!! Please use this format: event orientation day /from 2000-01-01 00:00 /to 2000-01-02 00:00");
    }
}
