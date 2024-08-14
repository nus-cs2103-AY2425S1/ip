package sigma.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Inherits from the {@link Task} class and adds a deadline by which the task should be completed.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task with the specified description and deadline.
     *
     * @param description the description of the task
     * @param by the deadline by which the task should be completed
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts this {@code Deadline} task to a string representation suitable for saving to a file.
     *
     * @return the string representation of this task in the format "D | status | description | by"
     */
    @Override
    public String stringify() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.by.format(formatter));
    }

    /**
     * Returns the string representation of this {@code Deadline} task.
     *
     * @return the string representation of this task in the format "[D][status] description (by: deadline)"
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }
}
