package bro.task;

/**
 * Represents a Deadline task tracked by Bro. A <code>DeadlineTask</code> object
 * is a type of task that has a specific deadline associated with it.
 */
public class DeadlineTask extends Task {
    private final String deadline;

    /**
     * Constructs a new DeadlineTask with the specified content and deadline.
     * The task is initially marked as not completed.
     *
     * @param content  The content of the Deadline task.
     * @param deadline The deadline of the task.
     */
    public DeadlineTask(String content, String deadline) {
        super(content);
        this.deadline = deadline;
    }

    /**
     * Constructs a new DeadlineTask with the specified content, deadline, and
     * completion status.
     *
     * @param content     The content of the Deadline task.
     * @param deadline    The deadline of the task.
     * @param isCompleted The initial completion status of the task.
     */
    public DeadlineTask(String content, String deadline, boolean isCompleted) {
        super(content, isCompleted);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of the Deadline task, indicating it is
     * a Deadline task along with its content, completion status, and deadline.
     *
     * @return A string representation of the Deadline task, prefixed with "[D]"
     *         and showing its deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
