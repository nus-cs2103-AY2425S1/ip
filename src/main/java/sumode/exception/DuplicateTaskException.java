package sumode.exception;

import sumode.task.Task;

/**
 * Thrown when user try to add a task with same task name as another task.
 */
public class DuplicateTaskException extends SumoDException {

    /**
     * Constructor for NonExistentTaskException
     * @param index index which duplicate task is at
     * @param task incumbent task which has same name of task to be added.
     */
    public DuplicateTaskException(Task task, int index) {
        super("Sumo sees this task already exists at index "
            + index
            + "\n"
            + task);
    }
}
