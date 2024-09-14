package darkpool.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import darkpool.DarkpoolException;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {

    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;

    /**
     * Constructs an Event task with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event.
     * @param fromTime The start time of the event in string format.
     * @param toTime The end time of the event in string format.
     * @param isDone The completion status of the event.
     * @throws DarkpoolException If the start or end time is not in the correct format.
     */
    public Event(String description, String fromTime, String toTime, boolean isDone) throws DarkpoolException {
        super(description.trim(), isDone);

        try {
            this.fromTime = LocalDateTime.parse(fromTime, Task.FORMATTER);
            this.toTime = LocalDateTime.parse(toTime, Task.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DarkpoolException(e.getMessage());
        }
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + this.description + " (from:" + this.fromTime.format(Task.FORMATTER)
                + " to:" + this.toTime.format(Task.FORMATTER) + ")";
    }

    /**
     * Returns a string representation of the Event task formatted for file storage.
     *
     * @return A string representation of the Event task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return ("E | " + (isDone ? "1" : "0") + " | " + this.description + " | "
                + this.fromTime.format(Task.FORMATTER) + " | " + this.toTime.format(Task.FORMATTER) + "\n");
    }
}
