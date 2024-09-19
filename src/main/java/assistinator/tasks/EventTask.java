package assistinator.tasks;

import java.time.LocalDateTime;

import assistinator.TaskStatus;

/**
 * Represents event task.
 */
public class EventTask extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Initialises an event task with start time and end time as LocalDateTime.
     * @param description Task description.
     * @param start Start time.
     * @param end End time.
     */
    public EventTask(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Initialises an event task with start time and end time as strings.
     * @param description Task description.
     * @param start Start time.
     * @param end End time.
     */
    public EventTask(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + String.format(" (from: %s to: %s)", start.format(formatter), end.format(formatter));
    }

    /**
     * {@inheritDoc}
     */
    public String toStorageString() {
        return String.format(
                "E | %s | %s | %s | %s",
                isDone ? TaskStatus.DONE : TaskStatus.NOTDONE,
                description,
                start.format(formatter),
                end.format(formatter)
        );
    }

    public LocalDateTime getStartTime() {
        return start;
    }

    public LocalDateTime getEndTime() {
        return end;
    }
}
