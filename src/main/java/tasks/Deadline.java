package tasks;

import java.time.LocalDateTime;

import parser.DateTimeHandler;

/**
 * The {@code Deadline} class represents a task with a specific deadline. It extends the
 * {@code Task} class and includes additional information about the due date and time for
 * the task.
 */
public class Deadline extends Task {

    private final LocalDateTime time;

    /**
     * Constructs a new {@code Deadline} task with the specified name and due date.
     *
     * @param name The name or description of the task.
     * @param time The due date and time for the task.
     */
    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    /**
     * Returns the due date and time for this deadline task.
     *
     * @return The due date and time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Returns a string representation of the deadline task, including its completion
     * status, name, and due date.
     *
     * @return A string representation of the task in the format "[D] name (by: dueDate)".
     */
    @Override
    public String toString() {
        String dueDate = DateTimeHandler.formatDateTime(this.time);
        return ("[D] " + super.toString() + " (by: " + dueDate + ")");
    }
}
