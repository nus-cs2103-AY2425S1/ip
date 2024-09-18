package justbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the Justbot application.
 * A Deadline is a task that must be completed by a specific date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline date/time.
     *
     * @param taskDescription The description of the task.
     * @param by The date and time by which the task must be completed.
     */
    public Deadline(String taskDescription, LocalDateTime by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline as a {@link LocalDateTime} object.
     */
    public LocalDateTime getDeadline() {
        return this.by;
    }

    /**
     * Returns a string representation of the deadline task.
     * The string includes the task type "[D]", the task description, and the formatted deadline.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, hhmma");
        String formattedBy = this.by.format(formatter).replace("AM", "hrs").replace("PM", "hrs");

        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}
