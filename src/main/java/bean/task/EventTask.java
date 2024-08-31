package bean.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start time and end time.
 */
public class EventTask extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an EventTask with the specified name, start time, and end time.
     * The start and end times are parsed from the provided strings.
     *
     * @param name The name of the event.
     * @param from The start time of the event in "yyyy-MM-dd HHmm" format.
     * @param to   The end time of the event in "yyyy-MM-dd HHmm" format.
     */
    public EventTask(String name, String from, String to) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * Retrieves the details of the event, including the start and end times in a readable format.
     *
     * @return A string representing the event details.
     */
    @Override
    public String getDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Converts the event task to a format suitable for saving to a file.
     *
     * @return A string representing the event task in a saveable format.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + name + " | " + from.format(formatter) + " | " + to.format(formatter);
    }

    /**
     * Returns a string representation of the event task, including its type, completion status, name, and details.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + getDetails();
    }
}
