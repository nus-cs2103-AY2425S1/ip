package james;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start and end time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private final String PATTERN = "MMM d yyyy HHmm";

    /**
     * Creates a new Event task.
     *
     * @param desc Description of the task
     * @param mark Whether the task is marked as done
     * @param start Start time of the event
     * @param end End time of the event
     * @throws MissingDescriptionException If the description is missing
     */
    public Event(String desc, Boolean mark, LocalDateTime start, LocalDateTime end) throws MissingDescriptionException {
        super(desc, mark);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return Task description with start and end times
     */
    @Override
    public String printTask() {
        return String.format("[E]%s (from: %s to: %s)", super.printTask(),
                start.format(DateTimeFormatter.ofPattern(PATTERN)),
                end.format(DateTimeFormatter.ofPattern(PATTERN)));
    }

    /**
     * Returns a string format of the task for saving to a file.
     *
     * @return Task details in a format suitable for file storage
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %s | %s | %s", super.toFileFormat(),
                start,
                end);
    }
}
