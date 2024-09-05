package Gary.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task that includes a description, start time, and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {
    protected LocalDateTime start;  // The start date and time of the event
    protected LocalDateTime end;    // The end date and time of the event

    // Formatter for outputting dates in a readable format
    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    // Formatter for parsing input dates from a specific pattern
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start       The start time of the event in "yyyy-MM-dd HHmm" format.
     * @param end         The end time of the event in "yyyy-MM-dd HHmm" format.
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
        return LocalDateTime.parse(dateTime, inputFormatter);
    }

    /**
     * Returns a string representation of the Event, including its status, description,
     * start time, and end time.
     *
     * @return A string representation of the Event.
     */
    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.description +
                " (from: " + this.start.format(outputFormatter) + " to: " + this.end.format(outputFormatter) + ")";
    }

    /**
     * Converts the Event into a format suitable for saving to a file.
     *
     * @return A string representation of the Event for file storage.
     */
    @Override
    public String parseToFile() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " +
                this.start.format(inputFormatter) + " | " + this.end.format(inputFormatter);
    }

    /**
     * Checks if this Event is equal to another object. Two Events are considered equal
     * if they have the same description, start time, end time, and completion status.
     *
     * @param o The object to compare with this Event.
     * @return true if the specified object is equal to this Event, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return super.equals(o) &&
                (this.start == null ? event.start == null : this.start.equals(event.start)) &&
                (this.end == null ? event.end == null : this.end.equals(event.end));
    }
}
