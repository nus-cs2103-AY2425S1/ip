package ponderpika.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a specific type of task that has a deadline.
 * It extends the {@code task} class and includes additional information about the deadline
 * by which the task must be completed.
 * The {@code Deadline} class overrides the following methods from the {@code task} class:
 * saveFullDetails() and toString()
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description the description of the task
     * @param deadline the deadline by which the task should be completed
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representing the full details of the task.
     * The format of the returned string is:
     * <pre>
     *   D | [completion status] | [description] | [deadline]
     * </pre>
     *
     * @return a string representing the full details of the task
     */
    @Override
    public String saveFullDetails() {
        return String.format("D | %b | %s | %s", isDone(), getDescription(),
                this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    /**
     * Returns a string representation of the task suitable for display.
     * The format of the returned string is:
     * <pre>
     *   [D][description] (by: [deadline])
     * </pre>
     *
     * @return a formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",
                this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
