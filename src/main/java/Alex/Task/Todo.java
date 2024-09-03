package Alex.Task;

/**
 * Represents a to-do task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the given description.
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the type of the task.
     * @return The type of the task (TODO).
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    /**
     * Gets the string representation of the Todo task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + description;
    }
}
