package revir.system.exceptions;

import revir.tasks.Task;

/**
 * Represents an exception that is thrown when a duplicate task is encountered.
 * This exception is typically thrown when a task is added to the task list but
 * a task with the same description (and date) already exists.
 */
public class DuplicateTaskException extends RuntimeException {
    /**
     * Constructs a new DuplicateTaskException with the specified task.
     *
     * @param task the task that caused the exception
     */
    public DuplicateTaskException(Task task) {
        super("Task already exists: \n" + task.toString());
    }
}
