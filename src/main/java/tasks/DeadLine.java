package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A DeadLine task has a description and a specific date and time by which it must be completed.
 */
public class DeadLine extends Task {
    private static final String TASK_TYPE = "deadline";
    private LocalDateTime by;

    /**
     * Constructs a DeadLine task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The date and time by which the task must be completed.
     */
    public DeadLine(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the DeadLine task, including its status, description, and deadline.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns a string format of the DeadLine task for saving to a file.
     *
     * @return A string formatted for saving to a file.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + super.toFileFormat() + " | " + by.format(formatter);
    }

    /**
     * Returns the type of the task as a string ("deadline").
     *
     * @return The string "deadline" representing the task type.
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Retrieves the deadline of the task as a LocalDateTime object.
     *
     * @return The date and time by which the task must be completed.
     */
    public LocalDateTime getBy() {
        return this.by;
    }
}
