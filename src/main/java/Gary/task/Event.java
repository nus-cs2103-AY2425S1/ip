package Gary.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task that includes a description, start time, and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {

    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // Start date and time of the event
    private LocalDateTime start;
    // End date and time of the event
    private LocalDateTime end;

    /**
     * Constructs an {@code Event} object with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event in "yyyy-MM-dd HHmm" format.
     * @param end The end time of the event in "yyyy-MM-dd HHmm" format.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = parseDateTime(start);
        this.end = parseDateTime(end);
    }

    /**
     * Parses a date-time string into a LocalDateTime object using the inputFormatter.
     *
     * @param dateTime The date-time string to be parsed.
     * @return The parsed LocalDateTime object.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, INPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the Event, including its status, description,
     * start time, and end time.
     *
     * @return A string representation of the Event.
     */
    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.description
                + " (from: " + this.start.format(OUTPUT_FORMATTER) + " to: " + this.end.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Converts the Event into a format suitable for saving to a file.
     *
     * @return A string representation of the Event for file storage.
     */
    @Override
    public String parseToFile() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | "
                + this.start.format(INPUT_FORMATTER) + " | " + this.end.format(INPUT_FORMATTER);
    }

    /**
     * Checks if this {@code Event} is equal to another object.
     * Two events are considered equal if they have the same description, start time, and end time.
     *
     * @param obj The object to compare with this {@code Event}.
     * @return {@code true} if the specified object is equal to this {@code Event}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Event otherEvent = (Event) obj;
        return super.equals(otherEvent)
                && (this.start == null ? otherEvent.start == null : this.start.equals(otherEvent.start))
                && (this.end == null ? otherEvent.end == null : this.end.equals(otherEvent.end));
    }
}
