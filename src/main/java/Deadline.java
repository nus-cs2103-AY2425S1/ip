/**
 * Represents a deadline task which has a deadline.
 */
public class Deadline extends Task{
    /**
     * The deadline of the task.
     */
    protected String by;

    /**
     * Creates a deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
