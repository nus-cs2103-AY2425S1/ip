package task;

import java.time.LocalDateTime;

/**
 * A Task with a deadline.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructor for a new Deadline task.
     *
     * @param description the description of the task.
     * @param deadline the deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * constructor for a new Deadline with the specified status of the Deadline.
     *
     * @param description the description of the task.
     * @param isComplete the status of the task.
     * @param deadline the deadline of the task.
     */
    public Deadline(String description, boolean isComplete, String deadline) {
        super(description, isComplete);
        this.deadline = deadline;
    }

    /**
     * Return the dateline of the task.
     *
     * @return the dateline of the task.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Return the String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
