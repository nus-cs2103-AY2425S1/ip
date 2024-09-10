package arts.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs within a specified time frame.
 * Inherits from the Task class and includes additional information
 * about the start and end times of the event.
 */
public class Event extends Task {
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter FILE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private LocalDateTime from;
    private LocalDateTime to;

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
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                from.format(DISPLAY_FORMATTER),
                to.format(DISPLAY_FORMATTER));
    }

    /**
     * Returns a string representation of the event task formatted for file storage.
     * This includes the task type, completion status, description, start time, and end time.
     *
     * @return A string formatted for storing the event task in a file.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %s | %s | %s | %s",
                isDone ? "1" : "0",
                description,
                from.format(FILE_FORMATTER),
                to.format(FILE_FORMATTER));
    }
}
