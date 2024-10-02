package bing.task;

import bing.task.Task;
import bing.task.TaskStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a time period between a start and end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    /**
     * Constructs an Event task with the specified information, start time, and end time.
     *
     * @param info The description of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String info, LocalDateTime from, LocalDateTime to) {
        super(info);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string that describes the Event task in a human-readable format,
     *         including its status, description, and time period.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatus().getStatusSymbol() + "]" + " " + getInfo() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the Event task in a format suitable for file storage.
     *
     * @param formatter The DateTimeFormatter used to format the start and end times.
     * @return A string that represents the Event task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return "E | " + (getStatus() == TaskStatus.DONE ? "1" : "0") + " | " + getInfo() + " | " + from.format(formatter) + " | " + to.format(formatter);
    }
}
