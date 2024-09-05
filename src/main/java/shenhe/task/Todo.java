package shenhe.task;

/**
 * Represents a to-do task.
 * This class extends {@link Task} to include basic task functionality with no additional fields.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the specified description and status.
     *
     * @param description the description of the to-do task
     * @param isDone      the status of the to-do task (true if done, false otherwise)
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the to-do task in a format suitable for saving to a file.
     * The format includes the task type ("T"), status icon, and description.
     *
     * @return a string representation of the to-do task in file format
     */
    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + description;
    }

    /**
     * Returns a string representation of the to-do task.
     * The string includes the task type and the status icon, followed by the task description.
     *
     * @return a string representation of the to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
