package bibi.task;

/**
 * Represents a Task with a description and no deadline.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo with given description.
     *
     * @param description The description of the task to be completed.
     */
    public ToDo(String description) {
        // Call bibi.description.Task constructor
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
