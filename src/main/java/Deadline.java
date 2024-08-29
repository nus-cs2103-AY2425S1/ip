/**
 * Represents a Deadline task in the Angel application.
 * A Deadline task has a description and a due date/time by which the task must be completed.
 */
public class Deadline extends Task {

    /** The due date/time by which the task must be completed. */
    protected String by;

    /**
     * Constructs a new Deadline task with the specified description and due date/time.
     *
     * @param description The description of the deadline task.
     * @param by The due date/time by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, which includes its type,
     * status, description, and due date/time.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the Deadline task in a format suitable for saving to a file.
     *
     * @return A string in the format "D | status | description | due date/time".
     */
    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + by;
    }
}
