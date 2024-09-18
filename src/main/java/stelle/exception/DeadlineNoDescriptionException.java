package stelle.exception;

/**
 * Exception thrown when there is no description given during creation of a deadline.
 * Child of stelle.exception.TaskException.
 * @author Lee Ze Hao (A0276123J)
 */

public class DeadlineNoDescriptionException extends TaskException {
    public DeadlineNoDescriptionException() {
        super("The description of a deadline cannot be empty!");
    }
}
