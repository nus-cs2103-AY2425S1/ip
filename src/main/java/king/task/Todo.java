package king.task;

import java.time.LocalDateTime;

/**
 * Represents a task that is a to-do item.
 */
public class Todo extends Task {

    /**
     * Constructs a To-do task with the given description.
     *
     * @param description A brief description of the to-do item.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a To-do task with the given description and completion status.
     *
     * @param description A brief description of the to-do item.
     * @param isDone The completion status of the to-do item.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string representing the task's status and description.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    /**
     * Returns a string representation of the to-do task suitable for saving to a file.
     *
     * @return A string representing the task in a file format.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public LocalDateTime getDueDateTime() {
        return LocalDateTime.now();
    }
}
