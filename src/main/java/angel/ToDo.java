package main.java.angel;

/**
 * Represents a task that does not have a specific date or time attached to it.
 * This class extends the {@link Task} class and is used to represent a "ToDo" task.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The string format is "[T][status icon] description", where:
     * - [T] denotes the task type (ToDo).
     * - [status icon] is either "X" if the task is marked as done or " " if not.
     * - description is the description of the task.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the task in a format suitable for saving to a file.
     * The format is "T | isDone | description", where:
     * - T denotes the task type (ToDo).
     * - isDone is "1" if the task is completed, otherwise "0".
     * - description is the description of the task.
     *
     * @return A string representing the ToDo task in save format.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }
}
