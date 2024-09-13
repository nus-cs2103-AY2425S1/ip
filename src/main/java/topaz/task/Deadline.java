package topaz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a deadline.
 * A {@link Deadline} object has a description and a deadline date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a {@link Deadline} with the specified description and deadline date and time.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a {@link Deadline} with the specified description, completion status, and deadline date and time.
     *
     * @param description The description of the deadline task.
     * @param isDone The completion status of the task.
     * @param by The deadline date and time.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }
    /**
     * Returns a string representation of the {@link Deadline} for display purposes.
     * The format is: [D][status icon] description (by: formatted date and time).
     *
     * @return A string representing the {@link Deadline} task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm", Locale.ENGLISH)) + ")";
    }

    /**
     * Returns a string representation of the {@link Deadline} for saving to a file.
     * The format is: D | status | description | deadline.
     *
     * @return A string representing the {@link Deadline} task suitable for file storage.
     */
    @Override
    public String toFileRecord() {
        Integer status = isDone ? 1 : 0;
        return "D" + " | " + status + " | " + this.description
                + " | " + this.by;
    }

    /**
     * Returns a string representation of the {@link Deadline} for display purposes.
     * The format is: [D][status icon] description (by: formatted date and time).
     *
     * @return A string representing the {@link Deadline} task status.
     */
    @Override
    public String getStatus() {
        return "[D]" + "[" + super.getStatusIcon() + "]" + " " + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm", Locale.ENGLISH)) + ")";
    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof Deadline obj) {
            return obj.description.equals(this.description)
                    && obj.by.equals(this.by)
                    && obj.isDone == isDone;
        }
        return false;
    }
}
