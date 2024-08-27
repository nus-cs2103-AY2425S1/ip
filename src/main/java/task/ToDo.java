package task;

import task.Task;
import task.TaskType;

/**
 * Represents a task.ToDo task.
 */
public class ToDo extends Task {
    /**
     * Constructor for task.ToDo class.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(TaskType.TODO, description);
    }
}
