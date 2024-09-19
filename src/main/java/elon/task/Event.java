package elon.task;

import elon.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, which is a specific type of Task with a start and end date.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event task with the given description, status, start date, and end date.
     *
     * @param description the description of the Event task
     * @param isDone whether the task is completed or not
     * @param start the start date of the Event
     * @param end the end date of the Event
     */
    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     * The format is "[E][statusIcon] description (from: startDate to: endDate)",
     * where statusIcon is "X" if the task is done or a space if not done.
     * Description is the description of the task and dates are formatted as "MMM d yyyy".
     *
     * @return the string representation of the Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    /**
     * Returns a string suitable for saving to a file, representing the Event task.
     * The format is "E | status | description | startDate | endDate",
     * where status is "1" if the task is done, or "0" if not done.
     * Dates are represented in default LocalDate format.
     *
     * @return the string representation of the Event task for saving to a file
     */
    @Override
    public String toFileString() {
        return "E | " + (this.getIsDone()? "1" : "0")
                + " | " + super.toFileString() + " | "
                + this.start + " | " + this.end;
    }

    public void snooze(LocalDateTime newDate) {
        this.end = newDate;
    }
}
