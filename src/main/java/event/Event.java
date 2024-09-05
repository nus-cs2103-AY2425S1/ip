package event;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a specific start and end time.
 */
public class Event extends Task {
    protected LocalDate startTime;
    protected LocalDate endTime;
    /**
     * Constructs an Event task with the specified description, start time, end time, and input.
     *
     * @param description The description of the event task.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     * @param input The input used to create the event task.
     */
    public Event (String description, LocalDate startTime, LocalDate endTime, String input) {
        super(description, input);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    /**
     * Returns a string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
