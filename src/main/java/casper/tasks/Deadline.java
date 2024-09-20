package casper.tasks;

import static casper.utils.DateTimeUtils.convertDateTimeToString;

import java.time.LocalDateTime;

/**
 * Represents a deadline task with a description and a due date.
 * Inherits from {@link Task} and includes functionality for setting and displaying the deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a new {@code Deadline} with the specified description and due date.
     *
     * @param description A brief description of the task.
     * @param by The date and time by which the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new {@code Deadline} with the specified description, due date, and completion status.
     *
     * @param description A brief description of the task.
     * @param by The date and time by which the task is due.
     * @param isDone The completion status of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the date and time by which the task is due.
     *
     * @return A {@link LocalDateTime} object representing the due date and time of the task.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task, including the description and due date.
     *
     * @return A string in the format: "[D][completed] description (by: due date)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertDateTimeToString(this.by) + ")";
    }

    /**
     * Returns a string representation of the deadline task suitable for saving to a file.
     *
     * @return A string in the format: "deadline | done status | description | due date".
     */
    @Override
    public String toSaveString() {
        return String.format("deadline | %d | %s | %s",
                super.isDone ? 1 : 0,
                this.description,
                convertDateTimeToString(this.by));
    }
}

