package drbrown.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the DrBrown application.
 * The deadline task has a description, a status (completed or not), and an end date and time.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final LocalDateTime endDateTime;

    /**
     * Constructs a Deadline task with the specified status, description, and end date/time.
     *
     * @param isCompleted  The completion status of the task (true if completed, false otherwise).
     * @param description  The description of the task.
     * @param endDateTime  The end date and time of the deadline.
     */
    public Deadline(boolean isCompleted, String description, LocalDateTime endDateTime, Priority priority) {
        super(isCompleted, description, priority);
        assert endDateTime != null : "End time should not be null";
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the string representation of the deadline task in the format suitable for file storage.
     *
     * @return A string formatted for file storage representing the deadline task.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + endDateTime.format(FILE_DATE_TIME_FORMATTER);
    }

    /**
     * Returns the string representation of the deadline task in the format suitable for UI display.
     *
     * @return A user-friendly string that describes the urgency of meeting the deadline.
     */
    @Override
    public String toUiString() {
        return "Last night, Darth Vader came down from Planet Vulcan and told me that "
                + "if you don't meet this deadline... he'd melt your brain! So, better get moving!\n";
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return A string representation of the deadline task with its status, description, and end date/time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + endDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
