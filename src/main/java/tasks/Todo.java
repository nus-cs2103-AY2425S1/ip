package tasks;

/**
 * Represents a task with no due date.
 * Encapsulates the description of a task.
 */
public class Todo extends Task {

    /**
     * Initialises a Todo object.
     *
     * @param description description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initialises a Todo object.
     *
     * @param description description of the task.
     * @param priority priority of the task.
     */
    public Todo(String description, String priority) {
        super(description, priority);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return string of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
