package tasks;

/**
 * Represents a task with no due date.
 * Encapsulates the description of a task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     * @return string of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
