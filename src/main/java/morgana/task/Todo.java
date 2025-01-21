package morgana.task;

/**
 * Represents a task with a description.
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} with the specified description.
     */
    public Todo(String description) {
        super(TaskType.TODO, description);
    }
}
