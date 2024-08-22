/**
 * Represents a ToDo task without any date or time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task,
     * including its type, status icon, and description.
     *
     * @return The task's type "[T]", status icon, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
