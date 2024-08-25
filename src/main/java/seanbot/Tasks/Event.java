package seanbot.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the SeanBot application.
 * An Event task has a description, a start time, and an end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event in the format yyyy-MM-ddTHH:mm.
     * @param to The end time of the event in the format yyyy-MM-ddTHH:mm.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from);  
        this.to = LocalDateTime.parse(to);  
    }

    /**
     * Converts the Event task to a string suitable for saving to a file.
     *
     * @return A string representation of the Event task for file storage.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Returns a string representation of the Event task, including its status, start time, and end time.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
