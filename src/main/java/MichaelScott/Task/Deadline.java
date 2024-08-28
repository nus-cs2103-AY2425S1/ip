package MichaelScott.Task;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline in the MichaelScott.MichaelScott task-tracking program.
 * Extends the Task class and includes a deadline date and time.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline date and time for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline date and time for this task.
     *
     * @return The deadline date and time as a LocalDateTime object.
     */
    public LocalDateTime getDeadlineDate() {
        return by;
    }

    /**
     * Returns the description of this task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task in the format: [D][status] description (by: deadline).
     */
    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + desc + " (by: " + this.by + ")";
    }

    /**
     * Returns a string representation of the task for saving file.
     *
     * @return A string representing the task in a format suitable for file storage: D | status | description | deadline.
     */
    @Override
    public String toFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + desc + " | " + this.by + ")";
    }
}
