package dudu.task;

/**
 * Represents a to-do task
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do task.
     *
     * @param description Task description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns task in storage format.
     */
    @Override
    public String toStorageString() {
        return String.format("T | %s", super.toStorageString());
    }

    /**
     * Returns task in display format.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
