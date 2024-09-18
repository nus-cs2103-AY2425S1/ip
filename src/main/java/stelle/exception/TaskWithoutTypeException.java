package stelle.exception;

/**
 * Exception thrown when a stelle.task.Task's taskType is null, or is of stelle.task.Task.UNSET.
 * @author Lee Ze Hao (A0276123J)
 */

public class TaskWithoutTypeException extends TaskException {
    public TaskWithoutTypeException() {
        super("A task with no type has been found. This can't be right?");
    }
}
