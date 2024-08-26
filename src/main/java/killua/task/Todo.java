package killua.task;

/**
 * Represents a Todo task in the Killua application.
 * A Todo task is a basic type of task that only includes a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this Todo task in the format suitable for saving to a file.
     *
     * @return A string representation of the Todo task for saving.
     */
    @Override
    public String toSave() {
        return "T" + super.toSave();
    }

    /**
     * Returns a string representation of this Todo task in a user-friendly format.
     *
     * @return A string representation of the Todo task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


