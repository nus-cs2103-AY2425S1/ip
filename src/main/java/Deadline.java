/**
 * Deadline class includes the name of the task and a deadline for the task to be completed by.
 * Subclass of Task class.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline instance.
     *
     * @param description Name of deadline task.
     * @param by Deadline for task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
