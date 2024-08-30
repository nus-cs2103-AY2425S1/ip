package repsmax;

/**
 * Represents a task with a deadline.
 * A <code>Deadline</code> object corresponds to a task with a specific due date/time.
 */
public class Deadline extends Task {

    /** The date/time by which the task should be completed. */
    protected String by;

    /**
     * Constructs a new Deadline object with the specified description and due date/time.
     *
     * @param description The description of the task.
     * @param by The due date/time of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object, including its status and due date/time.
     *
     * @return A string representing the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string in the file format used to save the Deadline object to disk.
     *
     * @return A string representing the Deadline object in the file format.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
}


