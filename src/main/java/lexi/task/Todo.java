package lexi.task;

/**
 * Represents a Todo task in the Lexi application.
 * A Todo is a task that does not have any specific date or time associated with it.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified name.
     *
     * @param taskName The name of the Todo task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the string representation of the Todo task.
     * The string includes the type of task (indicated by "[T]") and the task's name and status.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
