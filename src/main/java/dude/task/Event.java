package dude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific duration in Dude.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     * Date and time must be in specific format: "yyyy-MM-dd HH:mm"
     *
     * @param description The description of the task.
     * @param from The start date and time of the task.
     * @param to The end date and time of the task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event task's data into a string format for saving to a file.
     *
     * @return A string representing the Event task's data for saving.
     */
    @Override
    public String taskToStringData() {
        return "E" + super.taskToStringData() + "|"
                + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "|"
                + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a string representation of the Event task, including its status, description, start time, and end time.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
