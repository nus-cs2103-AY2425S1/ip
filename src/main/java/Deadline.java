/**
 * The {@code Deadline} class represents a task that has a specific deadline by which it needs to be completed.
 * <p>
 * It extends the {@code Task} class and is marked with a "D" to indicate that it is a Deadline task.
 * </p>
 */
public class Deadline extends Task {

    /** The deadline by which the task should be completed. */
    protected String by;
    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline by which the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the {@code Deadline} task.
     * <p>
     * The string includes the task type identifier "[D]" followed by the status icon,
     * description, and the deadline by which the task needs to be completed.
     * </p>
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}