package soju.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Deadline} class represents a task of type "Deadline".
 * It extends the {@code Task} class and includes additional functionality
 * for managing deadline-specific details such as the due date.
 */
public class Deadline extends Task {
    protected LocalDate doneBy;

    /**
     * Constructs a new {@code Deadline} task with the specified description and due date.
     * The task is initially marked as not done.
     *
     * @param description The description of the deadline task.
     * @param doneBy The due date of the task.
     */
    public Deadline(String description, LocalDate doneBy) {
        super(description);
        this.doneBy = doneBy;
    }

    /**
     * Returns a string representation of the due date formatted as "MMM d yyyy".
     *
     * @return A formatted string representing the due date of the task.
     */
    public String printDate() {
        return doneBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the "Deadline" task.
     * The string includes the task type indicator "[D]", followed by the status, description,
     * and the due date.
     *
     * @return A string representation of the "Deadline" task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), printDate());
    }

    /**
     * Returns a string representation of the "Deadline" task formatted for saving to a file.
     * The format is "D | <status> | <description> | <due date>", where <status> is 1 if the task is done
     * and 0 if the task is not done. The due date is represented as an ISO-8601 string.
     *
     * @return A string representation of the "Deadline" task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, doneBy);
    }
}
