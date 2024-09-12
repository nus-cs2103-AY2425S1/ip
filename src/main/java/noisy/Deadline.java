package noisy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that extends the Task class.
 * A Deadline task includes a deadline by which the task must be completed.
 */
public class Deadline extends Task {

    private LocalDate deadline;

    /**
     * Constructs a Deadline task with the specified description, status, and deadline.
     *
     * @param description The task description.
     * @param isDone Whether the task is marked as done.
     * @param deadline The deadline by which the task should be completed.
     */
    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Formats the Deadline task in a specific format for saving into a file.
     * Format: D | description | status | deadline
     *
     * @return The formatted string representing the task.
     */
    @Override
    public String formatTask() {
        String status = isDone? "1" : "0";
        return "D | " + this.description + " | " + status + " | " + this.deadline;
    }

    /**
     * Returns the string representation of the Deadline task, including its status and deadline.
     * The deadline is formatted in "MMM d yyyy" format (e.g., Jan 1 2024).
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}