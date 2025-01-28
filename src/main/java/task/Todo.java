package task;

/**
 * Represents a task.Todo task, which has only a description.
 */
public class Todo extends Task {
    /**
     * Creates a task.Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.Todo task.
     *
     * @return The string representation of the task.Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
