package jade.task;

/**
 * Represents a task without any specific date or time.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo with the specified description and completion status.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo with the specified description and completion status.
     *
     * @param description The description of the todo.
     * @param isDone Whether the todo is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo in data format.
     *
     * @return The data format string representation of the todo.
     */
    @Override
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return The string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
