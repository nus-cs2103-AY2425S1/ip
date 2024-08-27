package task;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {
    /**
     * Constructor for to-do class.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(TaskType.TODO, description);
    }
}
