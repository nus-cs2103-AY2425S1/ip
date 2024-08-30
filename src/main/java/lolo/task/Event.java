package lolo.task;

import lolo.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task in the Lolo application.
 * An Event is a specific type of task that has a start and end date and time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the specified description, start date, and end date.
     *
     * @param description The description of the Event task.
     * @param from The start date and time of the Event task.
     * @param to The end date and time of the Event task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     * The string includes a prefix "[E]", the status icon, the description,
     * and the start and end dates formatted as "MMM dd yyyy, h:mm a".
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the Event task formatted for saving to a file.
     * The string includes the task type, status, description, start date, and end date formatted as "yyyy-MM-dd HHmm".
     *
     * @return The data string representation of the Event task.
     */
    @Override
    public String toDataString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter) + " | " + to.format(formatter);
    }
}
