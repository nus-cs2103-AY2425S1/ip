package rex.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Event} class represents a task that occurs over a specific time period.
 * It extends the {@code Task} class by adding start and end times for the event.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new {@code Event} task with the specified description, completion status, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param isDone      The completion status of the Event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including its type identifier "[E]",
     * the task details from the {@code Task} superclass,
     * and the event's start and end times formatted as "dd MMM yyyy HHmm".
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return "[E]" + super.toString() + "(from: " + from.format(outputFormat)
                + "HRS | to: " + to.format(outputFormat) + "HRS)";
    }

    /**
     * Returns a formatted string suitable for saving to a file. The format includes a type identifier "E",
     * followed by the formatted output from the {@code Task} superclass,
     * and the event's start and end times formatted as "dd-MM-yy HHmm".
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String formatter() {
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");
        return "E | " + super.formatter() + " | " + from.format(fileFormat)
                + " | " + to.format(fileFormat);
    }
}
