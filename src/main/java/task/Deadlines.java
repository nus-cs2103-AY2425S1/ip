package task;

import exceptions.BuddyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A {@code Deadlines} task contains a description and a specific date and time by which the task must be completed.
 */
public class Deadlines extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a {@code Deadlines} task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline    The deadline by which the task must be completed, as a {@code LocalDateTime}.
     */
    public Deadlines(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Returns the task type of this task.
     * For deadline tasks, the task type is represented by "D".
     *
     * @return A string "D" indicating this is a deadline task.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns a string representation of the task formatted for file storage.
     * The format follows: "D | [isDone] | [description] | [deadline]".
     *
     * @return A string representing the task formatted for saving to a file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, getDescription(), localDateTimeString(deadline));
    }

    /**
     * Returns a string representation of the task for display, including the deadline formatted as a human-readable string.
     *
     * @return A string representing the task, with the description and formatted deadline.
     */
    @Override
    public String toString() {
        return getDescription() + " (by: " + formatDate(deadline) + ")";
    }

    /**
     * Updates the deadline of the task.
     * The new deadline is provided as a string and will be parsed into a {@code LocalDateTime}.
     *
     * @param newDeadline The new deadline as a string.
     * @throws BuddyException If the new deadline cannot be parsed into a valid {@code LocalDateTime}.
     */
    public void updateDeadline(String newDeadline) throws BuddyException {
        this.deadline = stringToDate(newDeadline);
    }
}