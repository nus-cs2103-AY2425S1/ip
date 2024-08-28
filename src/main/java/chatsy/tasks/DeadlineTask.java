package chatsy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private LocalDate by;

    /**
     * Constructs a {@code DeadlineTask} with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline for the task.
     */
    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a {@code DeadlineTask} with the specified description, deadline, and completion status.
     *
     * @param description The description of the task.
     * @param by The deadline for the task.
     * @param isDone The completion status of the task.
     */
    public DeadlineTask(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Returns the deadline for this task.
     *
     * @return The deadline as a {@code LocalDate}.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns a string representation of this task, including its status and deadline.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + description + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the string format of this task for saving to a file.
     *
     * @return The save format of the task as a string.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}
