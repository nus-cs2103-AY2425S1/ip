package elon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which is a specific type of Task with a by date.
 */
public class Deadline extends Task {
    protected boolean isDone;
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the given description, status, and deadline date.
     *
     * @param description the description of the Deadline task
     * @param isDone whether the task is completed or not
     * @param by the deadline date of the task
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format is "[D][statusIcon] description (by: by)",
     * where statusIcon is "X" if the task is done, or a space " " if not done.
     * The by date is formatted as "MMM d yyyy".
     *
     * @return the string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    /**
     * Returns a string suitable for saving to a file, representing the Deadline task.
     * The format is "D | status | description | deadlineDate",
     * where status is "1" if the task is done, or "0" if not done.
     * The deadline date is represented in default LocalDate format.
     *
     * @return the string representation of the Deadline task for saving to a file
     */
    @Override
    public String toFileString() {
        return "D | " + (this.getIsDone() ? "1" : "0")
                + " | " + super.toFileString() + " | "
                + this.by;
    }

    public void snooze(LocalDateTime newDate) {
        this.by = newDate;
    }
}
