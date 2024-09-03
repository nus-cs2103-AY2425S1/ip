package drbrown.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the DrBrown application.
 * An event task has a description, a status (completed or not), a start date/time, and an end date/time.
 */
public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs an Event task with the specified status, description, start date/time, and end date/time.
     *
     * @param status      The completion status of the task (true if completed, false otherwise).
     * @param description The description of the event.
     * @param start       The start date and time of the event.
     * @param end         The end date and time of the event.
     */
    public Event(boolean status, String description, LocalDateTime start, LocalDateTime end) {
        super(status, description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the event task in the format suitable for file storage.
     *
     * @return A string formatted for file storage representing the event task.
     */
    @Override
    public String toFileString() {
        return "E | " + this.getStatus() + " | " + this.getDescription() + " | " + this.start.format(FILE_DATE_TIME_FORMATTER) + " | " + this.end.format(FILE_DATE_TIME_FORMATTER);
    }

    /**
     * Returns the string representation of the event task in the format suitable for UI display.
     *
     * @return A user-friendly string that describes the scheduled event.
     */
    @Override
    public String toUIString() {
        return "The appropriate question is: ‘When the hell are they?’ Your event is now set in time!\n";
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return A string representation of the event task with its status, description, start date/time, and end date/time.
     */
    @Override
    public String toString() {
        return "[E][" + (this.getStatus() ? "X" : " ") + "] " +
                this.getDescription() + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " end: " + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

}
