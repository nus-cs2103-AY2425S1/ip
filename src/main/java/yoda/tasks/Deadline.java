package yoda.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a Deadline task with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param by The date by which the task should be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a formatted string representing the deadline task, to be saved to a file.
     *
     * @return A string in the format "D | isDone | description | by".
     */
    @Override
    public String getData() {
        String isDone = this.isDone ? "1" : "0";
        return "D | " + isDone + " | " + this.description + " | "
                + this.by;
    }

    /**
     * Returns a string representation of the deadline task, including its status, description, and deadline date.
     *
     * @return A string in the format "[D][status] description (by: date)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + ")";
    }
}
