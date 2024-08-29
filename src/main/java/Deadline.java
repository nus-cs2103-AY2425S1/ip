/**
 * Represents a type of Task that has a deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline Task with the given description and deadline.
     *
     * @param description The String description of the task.
     * @param by The Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDeadline() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + by + ")";
    }
}

