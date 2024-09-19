package tecna.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents the Deadline type of Task.
 * A <code>by</code> attribute stores the due date of the task.
 *
 * @author DennieDan.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task instance.
     *
     * @param taskName Name of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String taskName, LocalDateTime by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with <code>isDone</code>,
     * mostly when parsing tasks from the storage file.
     *
     * @param taskName Name of the task.
     * @param isDone Done status of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String taskName, boolean isDone, LocalDateTime by) {
        super(taskName);
        this.by = by;
        if (isDone) {
            super.markAsDone();
        }
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[D]" + super.toString() + " (by: " + this.by.format(pattern) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Deadline deadline = (Deadline) obj;
        return taskName.equals(deadline.taskName) && by.equals(deadline.by);
    }

    @Override
    public int hashCode() {
        return taskName.hashCode() + 31 * by.hashCode();
    }
}
