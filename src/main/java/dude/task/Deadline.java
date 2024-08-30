package dude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in Dude.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline with the specified description and deadline.
     * Date and time must be in specific format: "yyyy-MM-dd HH:mm"
     *
     * @param description The description of the task.
     * @param by The date and time represent the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadline task's data into a string format for saving to a file.
     *
     * @return A string representing the Deadline task's data for saving.
     */
    @Override
    public String taskToStringData() {
        return "D" + super.taskToStringData() + "|" + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a string representation of the Deadline task, including its status, description and deadline.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
