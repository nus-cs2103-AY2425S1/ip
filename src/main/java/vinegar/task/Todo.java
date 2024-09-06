package vinegar.task;

/**
 * Represents a Todo task in the Vinegar application.
 * <p>
 * A Todo task contains only a description and does not have any date or time associated with it.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string in the format "[T][ ] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task to a file-friendly format.
     *
     * @return A string in the format "T | isDone | description".
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
