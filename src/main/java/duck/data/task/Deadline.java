package duck.data.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task with a deadline that must be completed by a specified date and time.
 * The Deadline class extends {@link Task} and implements {@link Datable} to provide
 * formatting for file storage and display.
 */
public class Deadline extends Task implements Datable {

    /** The date and time by which the task must be completed. */
    private LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with the specified completion status, description, and deadline.
     *
     * @param isDone Indicates whether the task is completed.
     * @param description The description of the task.
     * @param by The date and time by which the task must be completed.
     */
    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Checks if the task is due on a specified date.
     *
     * @param date The date to check against the task's deadline.
     * @return True if the task's deadline is on the specified date; false otherwise.
     */
    public boolean isOn(LocalDate date) {
        return by.toLocalDate().equals(date);
    }

    /**
     * Returns a string representation of the task in a format suitable for file storage.
     * The format is "D | [TaskFormat] | yyyy-MM-dd HHmm".
     *
     * @return A string representation of the task in file format.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + getFileDateString(by);
    }

    /**
     * Returns a string representation of the task in a user-friendly format.
     * The format is "[D][TaskStatus](by: MMM dd yyyy HH:mm)".
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by: " + getDisplayDateString(by) + ")";
    }
}
