package samson.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import samson.SamException;

/**
 * The <code> Deadline </code> class represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a <code> Deadline </code> task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline by which the task should be completed, in the format "yyyy-MM-dd HHmm".
     * @throws SamException If the provided date format is invalid.
     */
    public Deadline(String description, String by) throws SamException {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeException e) {
            throw new SamException("Invalid date format! Please use yyyy-MM-dd HHmm.\n " +
                    "Example call: deadline your_task /by yyyy-MM-dd HHmm");
        }
    }

    /**
     * Returns the deadline in the format "yyyy-MM-dd HHmm".
     *
     * @return The deadline as a formatted string.
     */
    private String getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return by.format(formatter);
    }

    /**
     * Returns the deadline in a more readable format "MMM dd yyyy HH:mm".
     *
     * @return The deadline as a readable formatted string.
     */
    private String getMeaningfulBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return by.format(formatter);
    }

    /**
     * Returns a string representation of the task for storage in a file.
     *
     * @return The string representation of the task for storage.
     */
    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "D | 1 | " + this.getDescription() + " | " + this.getBy();
        } else {
            return "D | 0 | " + this.getDescription() + " | " + this.getBy();
        }
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getMeaningfulBy() + ")";
    }
}
