package stelle.exception;

/**
 * Exception thrown when there is no description given during creation of a stelle.task.ToDo.
 * Child of stelle.exception.TaskException.
 * @author Lee Ze Hao (A0276123J)
 */

public class ToDoNoDescriptionException extends TaskException {
    public ToDoNoDescriptionException() {
        super("The description of a todo cannot be empty!");
    }
}
