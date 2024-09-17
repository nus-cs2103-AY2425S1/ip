package Tasks;

import Tasks.Task;
import java.time.LocalDateTime;

/**
 * Represents an event, which is a type of task that occurs within a specific time frame.
 * An event has a description, a start time (from), and an end time (to).
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    protected LocalDateTime from;

    /**
     * The end time of the event.
     */
    protected LocalDateTime to;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event.
     * The string includes the event type "[E]", the task status, the description,
     * and the time range (from: start time to: end time).
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representing the event in a file-friendly format.
     * The format is: "E | status (1 for done, 0 for not done) | description | from | to".
     *
     * @return A string representing the event in file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
