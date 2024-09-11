package rizz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * Represents an Event task, which is a type of Task that has a start and end time.
 * An Event task contains a description, start time (from), and end time (to).
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalTime to;
    DateTimeFormatter writeDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    DateTimeFormatter readDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    DateTimeFormatter writeTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
    DateTimeFormatter readTimeFormatter = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Constructs a new Event task.
     *
     * @param text The description of the Event.
     * @param from The start time of the Event.
     * @param to The end time of the Event.
     * @param isDone The completion status of the Event. If true, the Event is marked as done.
     */
    public Event(String text, LocalDateTime from, LocalTime to, boolean isDone) {
        super(text,isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Exports the Event task as a formatted string for saving.
     * The format will be: "E | <isDone> | <text> | <from> <to>"
     *
     * @return The string representation of the Event task for file storage.
     */
    @Override
    public String export() {
        return "E | " + (isDone ? "1" : "0") + " | " + text + " | " + from.format(readDateTimeFormatter) + " " +
                to.format(readTimeFormatter);
    }

    /**
     * Returns a string representation of the Event task.
     * The format will be: "[E][X] <text> (at: <from>-<to>)"
     * where [X] indicates the task is done, and [ ] indicates it is not done.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + from.format(writeDateTimeFormatter) + "-" +
                to.format(writeTimeFormatter) + ")";
    }
}