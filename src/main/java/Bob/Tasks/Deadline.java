package bob.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task with a specific due date and time.
 * Inherits from Task class, and includes additional detais specific
 * to Deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructs a Deadline task with the specified  description and due date and time.
     * @param description the description of the deadline.
     * @param by the due date and time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a formatted string representation of the Deadline task to be saved to a file.
     * The format includes: task type, completion status, description, due date and time.
     */
    @Override
    public String fileFormat() {
        return "D | " + super.fileFormat() + " | " + this.by.format(INPUT_FORMATTER);
    }

    /**
     * Updates the due date and time of the deadline task.
     * @param newDeadline
     */
    public void updateDeadline(LocalDateTime newDeadline) {
        this.by = newDeadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DISPLAY_FORMATTER) + ")";
    }
}
