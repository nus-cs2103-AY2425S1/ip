package colress.exception;

/**
 * An exception that is thrown when there is an invalid task type entered by the user.
 */
public class UnknownTaskTypeException extends Exception {
    /**
     * Constructs an UnknownTaskTypeException with the given error message.
     */
    public UnknownTaskTypeException() {
        super("""
                I do not recognise that task type.
                Here are the task types I recognise:
                todo: add a new task item to your list.
                deadline: add a new task item with a deadline to your list.
                event: add a new event to your list.
                Try again."""
        );
    }
}
