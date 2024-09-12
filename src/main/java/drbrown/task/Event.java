package drbrown.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the DrBrown application.
 * An event task has a description, a completion status (completed or not), a priority level,
 * a start date/time, and an end date/time. It provides methods to format the task details
 * for file storage and UI display.
 */
public class Event extends Task {

    private static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructs a new Event task with the specified completion status, description, start date/time, end date/time, and priority level.
     *
     * @param isCompleted  A boolean indicating whether the task is completed (true if completed, false otherwise).
     * @param description  A string that describes the event.
     * @param startDateTime  A {@link LocalDateTime} object representing the start date and time of the event; must not be null.
     * @param endDateTime  A {@link LocalDateTime} object representing the end date and time of the event; must not be null.
     * @param priority  A {@link Priority} enum value representing the priority level of the task.
     * @throws IllegalArgumentException if the start or end date/time is null.
     */
    public Event(boolean isCompleted, String description, LocalDateTime startDateTime,
                 LocalDateTime endDateTime, Priority priority) {
        super(isCompleted, description, priority);
        assert startDateTime != null : "Start time should not be null";
        assert endDateTime != null : "End time should not be null";
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the string representation of the event task in a format suitable for file storage.
     * The format includes the type of task (Event), its completion status, description, priority,
     * start date/time, and end date/time, separated by vertical bars.
     *
     * @return A string formatted for file storage representing the event task.
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | "
                + startDateTime.format(FILE_DATE_TIME_FORMATTER) + " | "
                + endDateTime.format(FILE_DATE_TIME_FORMATTER);
    }

    /**
     * Returns the string representation of the event task in a format suitable for UI display.
     * This method provides a user-friendly message that humorously describes the scheduled event.
     *
     * @return A user-friendly string that humorously describes the scheduled event.
     */
    @Override
    public String toUiString() {
        return "The appropriate question is: 'When the hell are they?' Your event is now set in time!\n";
    }

    /**
     * Returns the string representation of the event task for console or text display.
     * This format includes the type of task (Event), its completion status, description,
     * start date/time, and end date/time.
     *
     * @return A string representation of the event task, including its status, description,
     *     start date/time, and end date/time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " end: "
                + endDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
