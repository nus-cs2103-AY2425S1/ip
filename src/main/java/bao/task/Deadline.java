package bao.task;

import java.time.LocalDateTime;

import bao.main.Bao;

/**
 * The Deadline class represents a task that has a specific deadline.
 * It extends the Task class, adding a deadline on top of the description to the task.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param description Description of the deadline task.
     * @param deadline LocalDateTime of when the task should be completed.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        assert deadline != null : "Deadline should not be null";
        this.deadline = deadline;
    }

    public LocalDateTime getDate() {
        return deadline;
    }

    /**
     * Returns a string representation of the deadline task, prefixed with "D" to indicate it is a deadline task,
     * and includes the formatted deadline.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + deadline.format(Bao.getOutputDateFormat())
                + getTagsAsString();
    }

    /**
     * Returns a string representation of the deadline task for saving, prefixed with "D" to indicate
     * it is a deadline task, and includes the formatted deadline.
     *
     * @return String representation of the deadline task for file storage.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description.trim() + " | "
                + deadline.format(Bao.getFileDateFormat()) + getTagsAsString();
    }
}
