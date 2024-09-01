package meep.task;

/**
 * The {@code Deadline} class represents a task that has a specific deadline.
 * It extends the {@link Task} class by adding a deadline date and time.
 */
public class Deadline extends Task {

    /** The deadline by which the task must be completed. */
    private final String by;

    /**
     * Constructs a {@code Deadline} task with the specified description and deadline.
     *
     * @param description A description of the task.
     * @param by The deadline date and time by which the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the formatted string representation of the task for saving to a file.
     * The format includes the task type, completion status, description, and deadline.
     *
     * @return The formatted string for saving the task.
     */
    @Override
    public String getSaveFormat() {
        return "D|" + super.getSaveFormat() + "|" + by;
    }

    /**
     * Returns the string representation of the deadline task.
     * The format includes the task type, description, and deadline.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
