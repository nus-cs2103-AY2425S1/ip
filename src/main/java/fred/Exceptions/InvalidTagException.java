package fred.Exceptions;

/**
 * The InvalidTagException is thrown when a user attempts to add a tag
 * to a task in an invalid format in the Fred application.
 */
public class InvalidTagException extends FredException {

    /**
     * Constructs an InvalidTagException with a default error message
     * that guides the user to the correct format for tagging a task.
     */
    public InvalidTagException() {
        super("OOPS!!! Please use this format: tag 1 fun");
    }
}
