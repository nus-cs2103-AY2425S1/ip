package bobby.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that extends the Task class.
 * A Deadline task has a description and a date/time by which it needs to be completed.
 */
public class Deadlines extends Task {
    /**
     * The date and time by which the task should be completed.
     */
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified description and date/time.
     *
     * @param description A brief description of the task.
     * @param by The date and time by which the task should be completed.
     */
    public Deadlines(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toStore() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "D/" + super.getStatus() + "/" + description + "/" + by.format(formatter);
    }
    @Override
    public LocalDateTime getBy() {
        return by;
    }
    @Override
    public LocalDateTime getFrom() {
        return null;
    }

}
