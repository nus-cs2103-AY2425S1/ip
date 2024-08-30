package lexi.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline task in the Lexi application.
 * A deadline has a due date and time in addition to a name and status.
 */
public class Deadline extends DatedTask {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified name and due date/time.
     *
     * @param taskName The name of the deadline task.
     * @param by The due date and time for the task.
     */
    public Deadline(String taskName, LocalDateTime by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Returns the due date and time of the deadline.
     *
     * @return The due date and time of the deadline.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns the formatted due date and time as a string.
     *
     * @return The formatted due date and time as a string.
     */
    public String getFormattedDateAndTime() {
        return this.by.format(DatedTask.outputFormatter);
    }

    /**
     * Returns the string representation of the deadline task.
     * The string includes the type of task (indicated by "[D]"), the task's name,
     * status, and the due date and time.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.getFormattedDateAndTime() + ")";
    }
}
