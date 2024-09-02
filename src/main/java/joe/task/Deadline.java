package joe.task;

import java.time.LocalDate;

/**
 * The {@code Deadline} class represents a task that needs to be completed by a specific date.
 * It extends the {@code Task} class and provides specific behavior for serializing and displaying
 * deadline tasks.
 */
public class Deadline extends Task {
    /** The due date by which the task needs to be completed. */
    protected LocalDate by;

    /**
     * Constructs a new {@code Deadline} task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date by which the task needs to be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Serializes the {@code Deadline} task into a formatted string to be stored in a file.
     * The format is "D|isDone|description|by".
     *
     * @return A formatted string representing the {@code Deadline} task.
     */
    @Override
    public String serialize() {
        return String.format("D|%b|%s|%s", this.isDone, this.description, this.by);
    }

    /**
     * Returns a string representation of the {@code Deadline} task for displaying to the user.
     * The format is "[D][statusIcon] description (by: dueDate)".
     *
     * @return A string representing the {@code Deadline} task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
