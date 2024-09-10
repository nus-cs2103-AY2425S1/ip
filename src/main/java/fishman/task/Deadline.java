package fishman.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * This class extends the Task class, adding a deadline attribute to it.
 */
public class Deadline extends Task {
    /** The deadline in which the task should be completed by. */
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the task.
     * @param by The deadline in which the task should be completed by.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        assert description != null : "Description should not be null";
        assert by != null : "Deadline 'by' date should not be null";
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }
    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        return super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
