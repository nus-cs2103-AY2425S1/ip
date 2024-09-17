package stelle.exception;

/**
 * Exception thrown when there is no description given during creation of an stelle.task.Event.
 * Child of stelle.exception.TaskException.
 * @author Lee Ze Hao (A0276123J)
 */

public class EventIllogicalDateException extends TaskException {
    public EventIllogicalDateException() {
        super("The end time cannot be in front of the start time, you know...");
    }
}
