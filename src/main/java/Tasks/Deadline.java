package Tasks;

import Tasks.Task;
import java.time.LocalDateTime;

/**
 * Represents a deadline, which is a type of task that must be completed by a specific date and time.
 * A deadline has a description and a due date/time (by).
 */
public class Deadline extends Task {
    /**
     * The due date and time for the task.
     */
    protected LocalDateTime by;

    /**
     * Constructs a Deadline with the specified description and due date/time.
     *
     * @param description The description of the task.
     * @param by          The due date and time for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline.
     * The string includes the task type "[D]", the task status, the description,
     * and the due date/time.
     *
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representing the deadline in a file-friendly format.
     * The format is: "D | status (1 for done, 0 for not done) | description | by".
     *
     * @return A string representing the deadline in file format.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
