package mira;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task that starts and ends at specific date/times.
 */
public class Event extends Task {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final LocalDateTime from; // The start datetime of the event
    private final LocalDateTime to; // The end datetime of the event

    /**
     * Constructs a new Event task.
     * It contains specified description, start, and end datetime.
     *
     * @param description The description of the Event task.
     * @param from        The start datetime of the event.
     * @param to          The end datetime of the event.
     */
    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.from = LocalDateTime.parse(from, DEFAULT_FORMAT);
        this.to = LocalDateTime.parse(to, DEFAULT_FORMAT);
    }

    /**
     * Constructs a new Event task.
     * It contains specified description, start, and end datetime.
     *
     * @param description The description of the Event task.
     * @param from        The start date/time of the event.
     * @param to          The end date/time of the event.
     * @param isDone      The status of the event.
     */
    public Event(String description, String from, String to, boolean isDone) throws DateTimeParseException {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, DEFAULT_FORMAT);
        this.to = LocalDateTime.parse(to, DEFAULT_FORMAT);
    }

    /**
     * Returns the string format of Event task to be saved for storage.
     *
     * @return A specified format string for storage.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.from.format(DEFAULT_FORMAT)
                + " | " + this.to.format(DEFAULT_FORMAT);
    }

    /**
     * Returns a string representation of the Event task.
     * It includes its type, status icon, description, start and end date/times.
     *
     * @return The task's type "[E]", status icon, description, start and end date/times.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma");
        boolean isSameDay = this.from.toLocalDate().isEqual(this.to.toLocalDate());
        String endDateTime = isSameDay
                ? this.to.format(DateTimeFormatter.ofPattern("h:mma")) // only show time
                : this.to.format(dateTimeFormatter);
        String outputDateTime = isSameDay
                ? " (" + this.from.format(dateTimeFormatter) + "-" + endDateTime + ")"
                : " (from: " + this.from.format(dateTimeFormatter) + " to: " + endDateTime + ")";
        return "[E]" + super.toString() + outputDateTime;
    }
}
