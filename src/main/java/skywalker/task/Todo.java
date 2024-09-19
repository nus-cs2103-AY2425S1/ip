package skywalker.task;

/**
 * Represents a to-do task in the Skywalker application.
 * A to-do task is a simple task with a description and no specific date or time.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a string representation of the to-do task, including its type indicator "[T]"
     * and the task's status and description.
     *
     * @return A string representation of the to-do task in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
