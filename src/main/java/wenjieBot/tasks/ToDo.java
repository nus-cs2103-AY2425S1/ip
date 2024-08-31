package wenjieBot.tasks;

/**
 * The ToDo class represents a specific type of task that has no deadline or additional attributes.
 * It extends the Task class to provide a representation for tasks without any extra details.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo in a format suitable for storage.
     * The format includes the task type identifier ("T") and the task details.
     *
     * @return A string representation of the ToDo task for storage.
     */
    @Override
    public String toPrettierString() {
        return "T" + super.toPrettierString();
    }

    /**
     * Returns a string representation of the ToDo for display purposes.
     * The format includes a task type indicator ("[T]") and the task details.
     *
     * @return A string representation of the ToDo task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
