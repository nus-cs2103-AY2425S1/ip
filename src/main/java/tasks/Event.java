package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 * An event task includes a description, a start time, and an end time.
 */
public class Event extends Task {
    protected LocalDateTime startWhen;
    protected LocalDateTime endWhen;

    /**
     * Constructs an event task with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param startWhen the start time of the event
     * @param endWhen the end time of the event
     */
    public Event(String description, String priority, LocalDateTime startWhen, LocalDateTime endWhen) {
        super(description, priority);
        this.startWhen = startWhen;
        this.endWhen = endWhen;
    }

    /**
     * Returns the string representation of the event for display purposes.
     * The format includes the status, description, start time, and end time.
     *
     * @return the string representation of the event
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return this.getPriorityIcon() + " 🎉 | " + this.getStatusIcon() + " | " + this.description + " (from: " + this.startWhen.format(formatter) + " to: " + this.endWhen.format(formatter) + ")";
    }

    /**
     * Returns the string representation of the event suitable for saving to a file.
     * The format is machine-readable, including the status, description, start time, and end time.
     *
     * @return the string representation of the event for file storage
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return this.priority + " | E | " + this.getStatusIcon() + " | " + this.description + " | " + this.startWhen.format(formatter) + " | " + this.endWhen.format(formatter);
    }
}
