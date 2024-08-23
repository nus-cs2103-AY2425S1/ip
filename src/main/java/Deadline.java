/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline task with description and due date.
     *
     * @param description The description of the deadline.
     * @param by The due date of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of this deadline task.
     *
     * @return A string representation of this deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}