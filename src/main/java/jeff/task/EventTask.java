package jeff.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents an event.
 */
public class EventTask extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

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
        assert this.start != null : "Start period should not be null";
        assert this.end != null : "End period should not be null";
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
    public EventTask(
            String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
        assert this.start != null : "Start period should not be null";
        assert this.end != null : "End period should not be null";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getDateString(this.start, "MMM dd yyyy hh:mm a")
                + " to: " + this.getDateString(this.end, "MMM dd yyyy hh:mm a") + ")";
    }

    /**
     * Returns the string representation of the task to store in the task list file.
     *
     * @return File string representation of the task.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.getDateString(this.start, "yyyy-MM-dd HH:mm")
                + " | " + this.getDateString(this.end, "yyyy-MM-dd HH:mm");
    }

    /**
     * Checks if the given date lies during the event period.
     *
     * @param date Given date.
     * @return true if the given date lies during the event period and false otherwise.
     */
    @Override
    public boolean isOnThisDate(LocalDate date) {
        return !date.isBefore(this.start.toLocalDate()) && !date.isAfter(this.end.toLocalDate());
    }
}
