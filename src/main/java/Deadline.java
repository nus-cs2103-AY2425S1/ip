
/**
 * Represents a deadline task.
 * A Deadline object corresponds to a task represented by a description and a deadline date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline class.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return Deadline of the task.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}