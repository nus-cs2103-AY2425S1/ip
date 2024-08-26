package Dook.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, that has a description, start, and end time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Creates an Event with the specified description, start time, and end time.
     *
     * @param description The description of the Event.
     * @param start The start time of the Event.
     * @param end The end time of the Event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a formatted string to be written to a file.
     *
     * @return The formatted string for file storage.
     */
    @Override
    public String fileFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E | " + super.fileFormatted() + " | " + this.start.format(formatter) + " | " + this.end.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return "[E]" + super.toString() + " (From: " + this.start.format(formatter) + " To: " + this.end.format(formatter) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Event)) {
            return false;
        } else if (o == this) {
            return true;
        }

        Event e = (Event) o;

        return e.description.equals(this.description) && e.start.equals(this.start) && e.end.equals(this.end);
    }
}
