package hana.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event task with a description, start and end date.
 */
public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a Deadline task with description and deadline.
     *
     * @param description The description of task.
     * @param from The start date of task.
     * @param to The end date of task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the details of the task.
     *
     * @return The details of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns the details of the task to save as.
     *
     * @return The details of the task to save as.
     */
    @Override
    public String fileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter) + " | " + to.format(formatter);
    }

    /**
     * Returns the start date of the task.
     *
     * @return The start date of the task.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date of the task.
     *
     * @return The end date of the task.
     */
    public LocalDateTime getTo() {
        return to;
    }
}
