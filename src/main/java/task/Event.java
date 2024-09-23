package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a time range, from a start time to an end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Initializes an Event task with a description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    /**
     * Converts the Event task to a string format suitable for saving to a file.
     *
     * @return A string representation of the Event task for file storage.
     */
    @Override
    public String toFile() {
        return "E|" + getStatusIcon() + "|" + this.description + "|"
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + "|"
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    /**
     * Provides a string representation of the Event task for display.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}
