/**
 * Represents a Todo task, which has only a description.
 */
public class Todo extends Task {
    /**
     * Creates a Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
