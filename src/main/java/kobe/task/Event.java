package kobe.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Duke chatbot application.
 * An event task is a task that has a specific start and end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the specified name, start time, and end time.
     *
     * @param name The name or description of the event task.
     * @param from The start date and time of the event.
     * @param to   The end date and time of the event.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task for saving to a file.
     *
     * @return A formatted string representing the event task in a file-friendly format.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + name + " | " + from.format(formatter) + " | " + to.format(formatter);
    }

    /**
     * Returns the string representation of the event task, including its start and end times.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
