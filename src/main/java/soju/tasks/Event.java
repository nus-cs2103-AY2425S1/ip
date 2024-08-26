package soju.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Event} class represents a task of type "Event".
 * It extends the {@code Task} class and includes additional functionality
 * for managing event-specific details such as start and end times.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     * The task is initially marked as not done.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the end time of the event formatted as "MMM dd yyyy hh:mm a".
     *
     * @return A formatted string representing the end time of the event.
     */
    public String printToDate() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    /**
     * Returns a string representation of the start time of the event formatted as "MMM dd yyyy hh:mm a".
     *
     * @return A formatted string representing the start time of the event.
     */
    public String printFromDate() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    /**
     * Returns a string representation of the "Event" task.
     * The string includes the task type indicator "[E]", followed by the status, description,
     * and the start and end times.
     *
     * @return A string representation of the "Event" task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), printFromDate(), printToDate());
    }

    /**
     * Returns a string representation of the "Event" task formatted for saving to a file.
     * The format is "E | <status> | <description> | <from> - <to>", where <status> is 1 if the task is done
     * and 0 if the task is not done. The from and to times are represented as ISO-8601 strings.
     *
     * @return A string representation of the "Event" task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s - %s", isDone ? 1 : 0, description, from, to);
    }
}
