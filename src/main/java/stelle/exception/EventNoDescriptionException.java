package stelle.exception;

/**
 * Exception thrown when there is no description given during creation of an stelle.task.Event.
 * Child of stelle.exception.TaskException.
 * @author Lee Ze Hao (A0276123J)
 */

public class EventNoDescriptionException extends TaskException {
    public EventNoDescriptionException() {
        super("The description of an event cannot be empty!");
    }
}
