package tick.tasks;

/**
 * Represents a task which only has a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     *
     * @param description Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task suitable for file storage.
     * The format is "T | status | description".
     *
     * @return A string representation of the ToDo task for file storage.
     */
    @Override
    public String toStorageFormat() {
        return String.format("T | %s | %s", super.getStatus() ? "1" : "0",
                super.getDescription());
    }

    /**
     * Returns a string representation of the ToDo task.
     * The format is "[T][status] description".
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
