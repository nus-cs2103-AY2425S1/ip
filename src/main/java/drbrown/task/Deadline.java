package drbrown.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the DrBrown application.
 * A Deadline task has a description, a status (completed or not), a priority level, and an end date and time.
 * It provides methods to format the task details for file storage and UI display.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final LocalDateTime endDateTime;

    /**
     * Constructs a new Deadline task with the specified completion status, description, end date/time,
     * and priority level.
     *
     * @param isCompleted  A boolean indicating whether the task is completed (true if completed,
     *                    false otherwise).
     * @param description  A string that describes the task.
     * @param endDateTime  A {@link LocalDateTime} object representing the end date and time of the deadline;
     *                    must not be null.
     * @param priority     A {@link Priority} enum value representing the priority level of the task.
     * @throws IllegalArgumentException if the end date and time is null.
     */
    public Deadline(boolean isCompleted, String description, LocalDateTime endDateTime, Priority priority) {
        super(isCompleted, description, priority);
        assert endDateTime != null : "End time should not be null";
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the string representation of the deadline task in a format suitable for file storage.
     * The format includes the task type, status, description, priority, and end date/time.
     *
     * @return A string formatted for file storage, representing the deadline task.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + endDateTime.format(FILE_DATE_TIME_FORMATTER);
    }

    /**
     * Returns the string representation of the deadline task in a format suitable for UI display.
     * The format is a humorous reminder of the urgency of meeting the deadline.
     *
     * @return A user-friendly string that humorously describes the urgency of meeting the deadline.
     */
    @Override
    public String toUiString() {
        return "Last night, Darth Vader came down from Planet Vulcan and told me that "
                + "if you don't meet this deadline... he'd melt your brain! So, better get moving!\n";
    }

    /**
     * Returns the string representation of the deadline task, including its type, status, description,
     * and formatted end date/time.
     * The format is used primarily for logging or debugging purposes.
     *
     * @return A string representation of the deadline task, including its type, status, description, and end date/time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + endDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
