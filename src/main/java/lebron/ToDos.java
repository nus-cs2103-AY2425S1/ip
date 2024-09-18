package lebron;

/**
 * Represents a "To-Do" task. A ToDo is a type of task that only has a description
 * and a completion status. It does not have a date associated with it.
 */
public class ToDos extends Task {

    /**
     * Constructs a ToDos task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task, including its type,
     * status icon, and description.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", super.getStatusIcons(), super.description);
    }

    /**
     * Returns a string representation of the ToDo task formatted for file storage.
     * The format includes the task type, completion status, and description.
     *
     * @return A string representing the ToDo task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }

}
