package Naega.Task;

/**
 * Represents a Todo task, which is a task with only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
        assert description != null && !description.isEmpty() : "Todo description must not be null or empty";
    }

    /**
     * Returns a string representation of the Todo task in a user-friendly format.
     * The format includes the task type and description.
     *
     * @return a string representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task in a format suitable for saving.
     * The format includes the task type, completion status, and description.
     *
     * @return a string representation of the Todo task in save format
     */
    @Override
    public String toSaveFormat() {
        assert description != null && !description.isEmpty() : "Description must be valid when saving a task";
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}