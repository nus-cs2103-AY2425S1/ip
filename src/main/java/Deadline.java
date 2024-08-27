/**
 * Represents a Deadline task that needs to be completed by a specific date/time.
 */
public class Deadline extends Task {
    private final String by; // The deadline for the task

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the specified description, deadline and isDone.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline by which the task must be completed.
     * @param isDone      The status of the deadline.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Return the string format of Deadline task to be saved for storage.
     *
     * @return A specified format string for storage.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.by;
    }

    /**
     * Returns a string representation of the Deadline task,
     * including its type, status icon, description, and deadline.
     *
     * @return The task's type "[D]", status icon, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
