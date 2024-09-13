package dave.task;

/**
 * Represents a Todo task. A Todo task contains a description and a completion status.
 * It inherits from the Task class.
 */
public class Todo extends Task {

    /** Stores the formatted statement for writing to file */
    private String statement;

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
        assert description != null && !description.trim().isEmpty() : "Description of Todo cannot be null or empty";
    }

    /**
     * Converts the Todo task into a string format that is suitable for saving to a file.
     *
     * @return The formatted string representing the Todo task.
     */
    public String write() {
        statement = String.format("T | %d | %s\n", this.getIsDone() ? 1 : 0, this.getDescription());
        return statement;
    }

    /**
     * Returns the string representation of the Todo task for display purposes.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
