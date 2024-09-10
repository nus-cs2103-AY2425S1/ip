package Buu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task in the GPT application.
 * An Event is characterized by its description, start time, end time, and priority.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description A brief description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event in a format suitable for file storage.
     * The format includes the task type, completion status, description, start time, end time, and priority.
     *
     * @return A string representing the Event in file format.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s | %s | %d",
                isDone ? 1 : 0,
                description,
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                priority);
    }

    /**
     * Returns a string representation of the Event for display purposes.
     * The format includes the task type, completion status, description, start time, end time, and priority.
     *
     * @return A string representing the Event for display.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s) (Priority: %s)",
                isDone ? "X" : " ",
                description,
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")),
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")),
                getPriorityLabel());
    }

    /**
     * Returns the type of task as a string.
     *
     * @return A string representing the type of task.
     */
    @Override
    protected String getTaskType() {
        return "E";
    }
}
