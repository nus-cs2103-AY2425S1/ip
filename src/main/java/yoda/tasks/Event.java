package yoda.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date/time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates an Event task with the specified description, start date/time, and end date/time.
     *
     * @param description The description of the event.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a formatted string representing the event task, to be saved to a file.
     *
     * @return A string in the format "E | isDone | description | start | end".
     */
    @Override
    public String getData() {
        String isDone = this.isDone ? "1" : "0";
        return "E | " + isDone + " | " + this.description + " | " + this.start + " | " + this.end;
    }

    /**
     * Returns a string representation of the event task, including its status, description, start, and end date/time.
     *
     * @return A string in the format "[E][status] description (from: start to: end)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) + ")";
    }
}
