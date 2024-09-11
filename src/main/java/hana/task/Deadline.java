package hana.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a description and deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task with description and deadline.
     *
     * @param description The description of task.
     * @param deadline The deadline of task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the details of the task.
     *
     * @return The details of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(FORMATTER) + ")";
    }

    /**
     * Returns the details of the task to save as.
     *
     * @return The details of the task to save as.
     */
    @Override
    public String fileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + priority + " | " + description
                + " | " + deadline.format(FORMATTER);
    }

    /**
     * Returns deadline of the task.
     *
     * @return Deadline of the task.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }
}
