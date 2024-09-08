package noosy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event, which is a specific type of task with start and end times.
 */
public class Event extends Task {

    /**
     * The start time of the event.
     */
    private LocalDateTime start;

    /**
     * The end time of the event.
     */
    private LocalDateTime end;

    /**
     * The formatter used for parsing input date-time strings.
     */
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * The formatter used for formatting output date-time strings.
     */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs a new Event with the given name, start time, and end time.
     *
     * @param name  The name of the event.
     * @param start The start time of the event.
     * @param end   The end time of the event.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Event with the given name, status, start time, and end time.
     *
     * @param name   The name of the event.
     * @param status The completion status of the event.
     * @param start  The start time of the event.
     * @param end    The end time of the event.
     */
    public Event(String name, boolean status, LocalDateTime start, LocalDateTime end) {
        super(name, status);
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Returns a string representation of the event for file storage.
     *
     * @return A formatted string containing the event's status, name, start time, and end time.
     */
    @Override
    public String storeInFileAsString() {
        String startingTime = (this.start != null) ? start.format(OUTPUT_FORMATTER) : "Invalid start date";
        String endingTime = (this.end != null) ? end.format(OUTPUT_FORMATTER) : "Invalid end date";
        return String.format("E | %s | %s | %s - %s", super.storeInFileAsString(), this.name, startingTime, endingTime);
    }

    /**
     * Returns a string representation of the event, including its status, name, start time, and end time.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        String formattedStart = (this.start != null) ? start.format(OUTPUT_FORMATTER) : "Invalid start date";
        String formattedEnd = (this.end != null) ? end.format(OUTPUT_FORMATTER) : "Invalid end date";
        return String.format("[E]%s (from: %s to: %s)", super.toString(), formattedStart, formattedEnd);
    }
}
