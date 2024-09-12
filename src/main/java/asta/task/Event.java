package asta.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is scheduled to occur during a specific time period. An Event task has a start time and an end
 * time, in addition to a description. This class extends the Task class and adds start and end time attributes.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with a description, start time, end time, and a completion status.
     *
     * @param description The description of the event task.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     * @param isDone      The completion status of the task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task in a format suitable for saving to a file. The format is "E |
     * {status} | {description} | {from} | {to}", where {status} is "1" if the task is done, "0" otherwise.
     *
     * @return A string representing the Event task for file storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter) + " | " + to.format(
                formatter);
    }

    /**
     * Returns a string representation of the Event task, including its type, status, description, start time, and end
     * time. The format is "[E][{status}] {description} (from: {formatted start time} to: {formatted end time})".
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
