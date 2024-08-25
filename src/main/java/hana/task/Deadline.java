package hana.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a description and deadline.
 */
public class Deadline extends Task{
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }

    /**
     * Returns the details of the task to save as.
     *
     * @return The details of the task to save as.
     */
    @Override
    public String fileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(formatter);
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
