package shrimp.task;

/**
 * Represents a simple task that does not have a specific deadline or event time.
 * A {@code Todo} task has a description and a completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Marks this task as done and returns a new {@code Todo} instance with the updated status.
     *
     * @return A new {@code Todo} instance marked as done.
     */
    @Override
    public Todo markAsDone() {
        return new Todo(getDescription(), true);
    }

    /**
     * Marks this task as not done and returns a new {@code Todo} instance with the updated status.
     *
     * @return A new {@code Todo} instance marked as not done.
     */
    @Override
    public Todo markAsNotDone() {
        return new Todo(getDescription(), false);
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the type of the task, "[T]" for Todo.
     */

    public String getType() {
        return "[T]";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return getType() + super.toString();
    }
}