package blue.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {

    /** The deadline for this task. */
    protected LocalDateTime deadline;

    /**
     * Constructs a DeadlineTask with a description and a deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline for the task as a LocalDateTime object.
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a DeadlineTask with a description and a deadline in string format.
     *
     * @param description The description of the task.
     * @param deadline The deadline for the task as a string in the format "d/M/yyyy HHmm".
     * @throws IllegalArgumentException If the deadline string is in an invalid format.
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        try {
            // Ensure the format used for parsing matches the format used in the file
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.deadline = LocalDateTime.parse(deadline.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for deadline: " + deadline);
        }
    }

    /**
     * Returns a string representation of the task, including the deadline in a human-readable format.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        String formattedDeadline = deadline.format(formatter);
        return "[D] " + super.toString() + " (by: " + formattedDeadline + ")";
    }

    /**
     * Returns the string representation of the task formatted for saving to a file.
     *
     * @return The string formatted for saving to a file.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formattedDeadline = deadline.format(formatter);
        return "D | " + getStatusIcon() + " | " + getDescription() + " | " + formattedDeadline;
    }

    /**
     * Checks if this DeadlineTask is equal to another object.
     *
     * @param o The object to compare to.
     * @return true if the given object is equal to this task, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeadlineTask that = (DeadlineTask) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(deadline, that.deadline) && this.isDone == that.isDone;
    }
}
