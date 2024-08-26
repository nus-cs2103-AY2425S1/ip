package thanos.tasks;

import static thanos.utility.DateTimeUtility.format;

import java.time.LocalDateTime;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    /**
     * The deadline by which the task should be completed.
     */
    private final LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline} object with the specified description and deadline.
     *
     * @param description the description of the {@code Deadline} task.
     * @param deadline the deadline by which the {@code Deadline} task should be completed.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Checks if the given date matches the deadline of this task.
     * <p>
     * This method returns {@code true} if the provided date is equal to the deadline
     * of this task; otherwise, it returns {@code false}.
     * </p>
     *
     * @param date the date to check against the task's deadline.
     * @return {@code true} if the given date matches the deadline; {@code false} otherwise.
     */
    @Override
    public boolean checkDate(LocalDateTime date) {
        return date.equals(this.deadline);
    }

    /**
     * Returns a string representation of the {@code Deadline} task.
     *
     * @return a string representation of the {@code Deadline} task in the format
     *         "[D][status] description (by: deadline)".
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), format(this.deadline));
    }

    /**
     * Returns a string representation of the {@code Deadline} task suitable for saving to a file.
     *
     * @return a string representation of the {@code Deadline} task in the format "D | [status] description | deadline".
     */
    @Override
    public String toFileString() {
        return String.format("D | %s | %s", super.toFileString(), format(this.deadline));
    }
}
