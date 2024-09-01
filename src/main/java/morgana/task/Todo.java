package morgana.task;

/**
 * Represents a task with a description.
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(TaskType.TODO, description);
    }
}
