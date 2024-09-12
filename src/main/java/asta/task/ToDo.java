package asta.task;

/**
 * Represents a simple task with a description but no specific deadline or time period. This class extends the Task
 * class, providing basic task functionality.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description. The task is initially not done.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo task with the specified description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param isDone      The initial completion status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the ToDo task in a format suitable for saving to a file. The format is "T |
     * {status} | {description}", where {status} is "1" if the task is done, "0" otherwise.
     *
     * @return A string representing the ToDo task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the ToDo task, including its type and description. The format is
     * "[T][{status}] {description}".
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
