package bottle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that occurs within a specific time range.
 * Extends the Task class to include start and end times.
 */
public class Event extends bottle.task.Task {
    /**
     * The start time of the event.
     */
    protected LocalDateTime from;

    /**
     * The end time of the event.
     */
    protected LocalDateTime to;

    /**
     * Constructs a new Event with the specified description, start time, and end time.
     *
     * @param desc the description of the event
     * @param from the start time of the event
     * @param to   the end time of the event
     */
    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event, including its type, description,
     * start time, and end time.
     *
     * @return a string representation of the Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the Event formatted for file storage.
     * The format includes the task type, completion status, description, start time, and end time.
     *
     * @return a string representation of the Event in a file-friendly format
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "E | " + (isChecked ? "1 | " : "0 | ")
                + this.taskDesc + " | " + this.from.format(formatter) + " | " + this.to.format(formatter);
    }
}
