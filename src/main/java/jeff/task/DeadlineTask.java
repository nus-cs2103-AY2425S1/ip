package jeff.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task with a deadline
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for the DeadlineTask Class.
     * Marked as not done from the start.
     *
     * @param description Description of the task.
     * @param deadline Date and time of when the task is due.
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        assert this.deadline != null : "Deadline should not be null";
    }

    /**
     * Constructor for the DeadlineTask Class.
     * Whether the task is marked as done or not depends.
     *
     * @param description Description of the task.
     * @param deadline Date and time of when the task is due.
     * @param isDone Whether the task is completed or not.
     */
    public DeadlineTask(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
        assert this.deadline != null : "Deadline should not be null";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.getDateString(this.deadline, "MMM dd yyyy hh:mm a") + ")";
    }

    /**
     * Returns the string representation of the task to store in the task list file.
     *
     * @return File string representation of the task.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | "
                + this.getDateString(this.deadline, "yyyy-MM-dd HH:mm");
    }

    /**
     * Checks if the given date is on the same day as the deadline of the task.
     *
     * @param date Given date.
     * @return true if the given date is on the same day as the task's deadline and false otherwise.
     */
    @Override
    public boolean isOnThisDate(LocalDate date) {
        return date.equals(this.deadline.toLocalDate());
    }
}
