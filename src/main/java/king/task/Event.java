package king.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description A brief description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with the given description, start time, end time, and completion status.
     *
     * @param description A brief description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @param isDone The completion status of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representing the task's status, description, start time, and end time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);
        return "[E]" + "[" + this.getStatusIcon() + "]"
                + " " + this.description + " (from: " + formattedFrom + " to: "
                + formattedTo + ")";
    }

    /**
     * Returns a string representation of the event task suitable for saving to a file.
     *
     * @return A string representing the task in a file format.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public LocalDateTime getDueDateTime() {
        return this.to;
    }
}
