package bobby.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 * An Event is a type of task that occurs over a specified period, with a start date and an end date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs a new Event task with the given description, start date, and end date.
     *
     * @param description The description of the Event task.
     * @param from        The start date of the Event.
     * @param to          The end date of the Event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     * The format includes the type of task, the output of the Task's toString method,
     * and the start and end dates formatted as "MMM dd yyyy".
     *
     * @return A string in the format "[E][statusIcon] description (from: start_date to: end_date)".
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the Event task formatted for saving to a file.
     * The format is "E | isDone | description | start_date | end_date".
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "E" + " " + super.toFileString() + " | " + this.from + " | " + this.to;
    }

    /**
     * Checks if the Event occurs on the given date.
     *
     * @param date The date to check.
     * @return True if the Event starts or ends on the given date; otherwise, false.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return (from.equals(date) || to.equals(date));
    }
}
