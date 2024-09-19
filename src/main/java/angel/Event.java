package main.java.angel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that has a start and end date/time.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, HHmm");
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event instance with the given description, start, and end date/time.
     *
     * @param description Description of the event.
     * @param from        Start date/time of the event.
     * @param to          End date/time of the event.
     * @throws NullPointerException If either from or to is null.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        if (from == null || to == null) {
            throw new NullPointerException("Start and end times cannot be null.");
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Compares this event task with another object to check for equality.
     * Event tasks are considered equal if their descriptions, status (isDone), start times, and end times are the same.
     *
     * @param obj The object to compare with this event task.
     * @return true if the given object is equal to this event task, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return this.description.equals(other.description)
                    && this.from.equals(other.from)
                    && this.to.equals(other.to);
        }
        return false;
    }

    /**
     * Returns a string representation of the Event in a user-readable format.
     *
     * @return A string representation of the Event, including its status, description, start, and end date/time.
     */
    @Override
    public String toString() {
        // Format the date/time strings with commas
        return "[E][" + getStatusIcon() + "] " + description
                + " (from: " + from.format(OUTPUT_FORMAT)
                + ", to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns a string representation of the Event in a format suitable for saving to a file.
     *
     * @return A string representation of the Event in the save format.
     */
    @Override
    public String toSaveFormat() {
        // Save format includes the date/time in yyyy/MM/dd HHmm format
        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return "E | " + super.toSaveFormat()
                + " | " + from.format(saveFormat)
                + " | " + to.format(saveFormat);
    }
}
