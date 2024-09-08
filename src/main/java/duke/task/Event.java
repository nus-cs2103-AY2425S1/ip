package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs between a specific start and end date/time.
 * Inherits from the Task class.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with a description, start date/time, end date/time, and completion status.
     *
     * @param description The description of the event task.
     * @param from The start date/time of the event as a string in the format "yyyy-MM-dd HHmm".
     * @param to The end date/time of the event as a string in the format "yyyy-MM-dd HHmm".
     * @param isDone The completion status of the task. True if the task is done, false otherwise.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * Constructs an Event task with a description, start date/time, and end date/time. The task is initially not done.
     *
     * @param description The description of the event task.
     * @param from The start date/time of the event as a string in the format "yyyy-MM-dd HHmm".
     * @param to The end date/time of the event as a string in the format "yyyy-MM-dd HHmm".
     */
    public Event(String description, String from, String to) {
        this(description, from, to, false);
    }

    /**
     * Returns a string representation of the event task in a format suitable for saving to a file.
     *
     * @return A string representing the event task in the format "E | isDone | description | from | to".
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter) + " | " + to.format(formatter);
    }

    /**
     * Returns a string representation of the event task for display purposes.
     *
     * @return A string representing the event task in the format "[E][status] description (from: start date/time to: end date/time)".
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    @Override
    public LocalDateTime getDate() {
        return from;
    }
}

