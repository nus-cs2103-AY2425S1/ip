package bopes.task;

/**
 * The ToDo class represents a simple task without a specific deadline or time frame.
 * It extends the Task class and provides implementations for task string representation
 * and file format conversion.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description and completion status.
     *
     * @param description the description of the ToDo task
     * @param isDone      the completion status of the task
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        assert description != null && !description.isEmpty() : "Description cannot be null or empty.";
    }

    /**
     * Returns a string representation of the ToDo task, including its type and description.
     *
     * @return a string representation of the ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task formatted for file storage.
     * The format includes the task type, completion status, and description.
     *
     * @return a string representation of the ToDo task in a file-friendly format
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + this.description;
    }
}
