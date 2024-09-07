package duck.data.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents an event with a specified start and end date and time.
 * The Event class extends {@link Task} and implements {@link Datable} to provide
 * formatting for file storage and display.
 */
public class Event extends Task implements Datable {

    /** The date and time when the event starts. */
    private LocalDateTime from;

    /** The date and time when the event ends. */
    private LocalDateTime to;

    /**
     * Constructs an Event with the specified description, start date and time, and end date and time.
     *
     * @param description The description of the event.
     * @param start The date and time when the event starts.
     * @param end The date and time when the event ends.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.from = start;
        this.to = end;
    }

    /**
     * Constructs an Event with the specified completion status, description, start date and time,
     * and end date and time.
     *
     * @param isDone Indicates whether the event is completed.
     * @param description The description of the event.
     * @param start The date and time when the event starts.
     * @param end The date and time when the event ends.
     */
    public Event(boolean isDone, String description, LocalDateTime start, LocalDateTime end) {
        super(isDone, description);
        this.from = start;
        this.to = end;
    }

    public boolean isStartingBefore(LocalDate date) {
        return !from.toLocalDate().isAfter(date);
    }

    /**
     * Checks if the event ends after a specified date.
     *
     * @param date The date to check against the event's end date.
     * @return True if the event ends on or after the specified date; false otherwise.
     */
    public boolean isEndingAfter(LocalDate date) {
        return !to.toLocalDate().isBefore(date);
    }

    /**
     * Returns a string representation of the event in a format suitable for file storage.
     * The format is "E | [TaskFormat] | yyyy-MM-dd HHmm | yyyy-MM-dd HHmm".
     *
     * @return A string representation of the event in file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat()
                + " | " + getFileDateString(from)
                + " | " + getFileDateString(to);
    }

    /**
     * Returns a string representation of the event in a user-friendly format.
     * The format is "[E][TaskStatus](from: MMM dd yyyy HH:mm to: MMM dd yyyy HH:mm)".
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from: " + getDisplayDateString(from) + " "
                + "to: " + getDisplayDateString(to) + ")";
    }
}
