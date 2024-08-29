package fanny.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date and time.
 * Inherits from the {@link Task} class.
 */
public class Deadline extends Task {

    /** The due date and time of the deadline task. */
    private LocalDateTime deadline;

    /**
     * Constructs an {@code Deadline} with the specified description, and deadline.
     *
     * @param description A description of the deadline task.
     * @param deadline The due date and time of the deadline task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the deadline task, including its status,
     * description, and due date and time.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + getDeadline() + ")";
    }

    /**
     * Returns the formatted due date and time of the event.
     *
     * @return A string representing the deadline in the format "MMM dd, yyyy HH:mm".
     */
    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"));
    }

}
