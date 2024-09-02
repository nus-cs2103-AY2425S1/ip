package samson.task;

/**
 * The <code> ToDo </code> class represents a task with no specific time associated with it.
 */
public class ToDo extends Task {

    /**
     * Constructs a <code> ToDo </code> task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a string representation of the task for storage in a file.
     *
     * @return The string representation of the task for storage.
     */
    @Override
    public String toStorageString() {
        return (this.isDone() ? "T | 1 | " : "T | 0 | ") + this.getDescription();
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
