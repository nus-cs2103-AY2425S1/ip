package alex.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a start and end date and time.
 */
public class Event extends Task {
    private LocalDateTime start;

    private LocalDateTime end;
    public Event (String taskName, boolean isCompleted, LocalDateTime start, LocalDateTime end) {
        super(taskName, isCompleted);
        this.start = start;
        this.end = end;
    }

    /**
     * {@inheritDoc}
     *
     * Returns the String representation of the Task to be displayed to user
     * that includes its completion status, description as well as start and end dates.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + ")";
    }

    @Override
    public String toStorageString() {
        return "[E]" + super.toString() + " /from " + this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " /to " + this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
