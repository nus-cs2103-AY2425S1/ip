package LunaBot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task in the task list.
 * An Event task has a description, start date and time, end date and time
 * and a completion status (whether it is done or not).
 */
public class Event extends Task {

    private  LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with specified description, start date and time,
     * end date and time and completion status.
     *
     * @param description The description of the Event task.
     * @param from The start date and time of the Event task represented as LocalDateTime.
     * @param to The end date and time of the Event task represented as LocalDateTime.
     * @param isDone Whether the Event task is completed or not.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with specified description, start date and time,
     * end date and time and completion status set to not done.
     *
     * @param description The description of the Event task.
     * @param from The start date and time of the Event task represented as LocalDateTime.
     * @param to The end date and time of the Event task represented as LocalDateTime.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, from, to, false);
    }

    /**
     * Converts the Event task into a format suitable for saving to a file.
     *
     * @return A string representation of the Event task in the format to be stored in the file.
     *         The format is
     *         "E | 1 or 0 (done or not done) | description | yyyy-MM-dd HH:mm (start) | yyyy-MM-dd HH:mm (end)".
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter) + " | " + to.format(formatter);
    }

    /**
     * Returns the string representation of the Event task, including its description, completion status,
     * start date and time and end date and time.
     *
     * @return A string representation of the Event task.
     *         The format is
     *         "[E][X or ] description (from: MMM dd yyyy, h:mm a to: MMM dd yyyy, h:mm a)".
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) +")";
    }
}
