package elysia.tasks;

/**
 * Represents a deadline task with a specific due date.
 * Extends the Task class and includes a date/time by which the task should be completed.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with a description and a due date.
     *
     * @param description The description of the deadline task.
     * @param by The date and/or time by which the task is to be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadline task to a string format suitable for saving to a file.
     * The format is "D" followed by the format from Task, then a separator "|", and the due date.
     *
     * @return A string representation of the Deadline task in file format.
     */
    @Override
    public String toFile() {
        return "D" + super.toFile() + "|" + by;
    }

    /**
     * Returns a string representation of the Deadline task, including its description and due date.
     * The format is "[D]" followed by the format from Task, then "(by: due date)".
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public boolean containsString(String searchString) {
        return super.containsString(searchString) || by.contains(searchString);
    }
}
