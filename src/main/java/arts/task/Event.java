package arts.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs within a specified time frame.
 * Inherits from the Task class and includes additional information
 * about the start and end times of the event.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, including its type,
     * description, and formatted start and end times.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(outputFormatter) + " to: " + to.format(outputFormatter) + ")";
    }

    /**
     * Returns a string representation of the event task formatted for file storage.
     * This includes the task type, completion status, description, start time, and end time.
     *
     * @return A string formatted for storing the event task in a file.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(fileFormatter) + " | " + to.format(fileFormatter);
    }
}
