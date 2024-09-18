package justbot.task;

/**
 * Represents a Todo task in the Justbot application.
 * A Todo is a task without a specific date or time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param taskDescription The description of the Todo task.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Returns a string representation of the Todo task.
     * The string includes the task type "[T]" and the task description.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
