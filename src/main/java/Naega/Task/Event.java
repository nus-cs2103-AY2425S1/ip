package Naega.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description the description of the event task
     * @param from        the start date and time of the event
     * @param to          the end date and time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        assert from != null : "Event 'from' date should not be null";
        assert to != null : "Event 'to' date should not be null";
        assert from.isBefore(to) : "'From' date should be before 'to' date";

        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return a string describing the event, including the start and end times
     */
    @Override
    public String toString() {
        assert from != null && to != null : "'from' and 'to' dates should not be null";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hhmma");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns a string suitable for saving the Event task to a file.
     *
     * @return a string representation of the Event task for file storage
     */
    @Override
    public String toSaveFormat() {
        assert from != null && to != null : "'from' and 'to' dates should not be null when saving";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter) + " | " + to.format(formatter);
    }
}