package Buu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task in the GPT application.
 * An Event task has a description, a start date/time, and an end date/time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start date/time of the event.
     * @param end The end date/time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task in a format suitable for saving to a file.
     * The format is:
     * "E | done status | description | start date/time | end date/time | priority"
     *
     * @return A string representation of the Event task in file format.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s | %s | %d",
                isDone ? 1 : 0,
                description,
                start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // Updated format
                end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // Updated format
                priority);
    }

    /**
     * Returns a string representation of the Event task suitable for displaying to the user.
     * The format is:
     * "[E][done status] description (from: start date/time to: end date/time) (Priority: priority)"
     *
     * @return A string representation of the Event task for display.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s) (Priority: %s)",
                isDone ? "X" : " ",
                description,
                start.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")),
                end.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")),
                getPriorityLabel());
    }

    /**
     * Returns the type of the task, which is "E" for Event tasks.
     *
     * @return The type of the task as a string.
     */
    @Override
    protected String getTaskType() {
        return "E";
    }
}
