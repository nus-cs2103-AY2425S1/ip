package orion.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 *
 * <p>
 * A Deadline task has a description and a deadline by which the task needs to
 * be completed. It extends the base Task class and provides additional
 * functionality specific to deadlines.
 * </p>
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task.
     *
     * @param taskId      the unique identifier for the task.
     * @param description the description of the task.
     * @param by          the date and time by which the task must be completed.
     */
    public Deadline(int taskId, String description, LocalDateTime by) {
        super(taskId, description);
        this.by = by;
    }

    /**
     * Gets the deadline date and time for this task.
     *
     * @return the deadline date and time.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns the type icon for this task.
     *
     * @return a string representing the task type icon.
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /**
     * Returns a string representation of this task.
     *
     * <p>
     * The string representation includes the task's description and the
     * formatted deadline.
     * </p>
     *
     * @return a string representing the task, including the description and
     *         formatted deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
