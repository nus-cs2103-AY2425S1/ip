package lolo.task;

/**
 * Represents a ToDo task in the Lolo application.
 * A ToDo is a specific type of task with its own string representation.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The string includes a prefix "[T]" followed by the status icon and the description.
     *
     * @return The string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task formatted for saving to a file.
     * The string includes the task type, status, and description.
     *
     * @return The data string representation of the ToDo task.
     */
    @Override
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
