package nuffle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a task with a deadline. A deadline task has a specific date and time by which it must be completed.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date/time.
     *
     * @param description The description of the deadline task.
     * @param by The date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        // Constructor for Deadline class
        super(description);
        this.by = by;
    }

    /**
     * Returns the description of the deadline task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the deadline formatted as a readable string.
     * The format used is "MMM dd yyyy, h:mm a".
     *
     * @return The formatted deadline date and time.
     */
    public String getFormattedDeadline() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
    }

    /**
     * Returns a string representation of the deadline task,
     * including its type ([D]), status, description, and due date/time.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        // Add a [D] at the front of task description (parent class)
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }

    /**
     * Converts the deadline task to a specific string format for saving to a file.
     * The format includes whether the task is done, its description, and the deadline date/time in "yyyy-MMM-dd HHmm" format.
     *
     * @return A string representation of the deadline task suitable for saving to a file.
     */
    public String printSaveFormat() {
        String temp;
        if (isDone) {
            temp = "1";
        } else {
            temp = "0";
        }
        return "D | " + temp + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
    }
}
