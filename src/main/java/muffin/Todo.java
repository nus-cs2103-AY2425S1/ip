package muffin;

/**
 * Represents a task that needs to be done.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFormattedString() {
        return String.format("T|%s|%s", checkStatus(this), this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
