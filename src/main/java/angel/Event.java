package main.java.angel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that has a start and end date/time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, HHmm");

    /**
     * Constructs an Event instance with the given description, start, and end date/time.
     *
     * @param description Description of the event.
     * @param from        Start date/time of the event.
     * @param to          End date/time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        if (from == null || to == null) {
            throw new NullPointerException("Start and end times cannot be null.");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        // Format the date/time strings with commas
        return "[E][" + getStatusIcon() + "] " + description
                + " (from: " + from.format(OUTPUT_FORMAT)
                + ", to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toSaveFormat() {
        // Save format includes the date/time in yyyy/MM/dd HHmm format
        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return "E | " + super.toSaveFormat()
                + " | " + from.format(saveFormat)
                + " | " + to.format(saveFormat);
    }
}
