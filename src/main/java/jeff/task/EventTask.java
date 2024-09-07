package jeff.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jeff.parser.Parser;

/**
 * Represents an event.
 */
public class EventTask extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor to create a EventTask object.
     * Marked as not done from the start.
     *
     * @param description Description of the task.
     * @param start Start date and time of the event.
     * @param end End date and time of the event.
     */
    public EventTask(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor to create a EventTask object.
     * Whether the task is marked as done or not depends.
     *
     * @param description Description of the task.
     * @param start Start date and time of the event.
     * @param end End date and time of the event.
     * @param isDone Whether the task is completed or not.
     */
    public EventTask(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                Parser.toDateTimeString(this.start),
                Parser.toDateTimeString(this.end)
        );
    }

    /**
     * Returns the string representation of the task to store in the task list file.
     *
     * @return File string representation of the task.
     */
    @Override
    public String toFileString() {
        return String.format(
                "E | %s | %s | %s",
                super.toFileString(),
                Parser.toDateTimeString(this.start),
                Parser.toDateTimeString(this.end)
        );
    }

    /**
     * Checks if the given date lies during the event period.
     *
     * @param date Given date.
     * @return true if the given date lies during the event period and false otherwise.
     */
    @Override
    public boolean isOnThisDate(LocalDate date) {
        return !date.isBefore(this.start.toLocalDate())
                && !date.isAfter(this.end.toLocalDate());
    }
}
