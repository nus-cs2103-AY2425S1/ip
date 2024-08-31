package orion.tasks;

/**
 * Represents a task that does not have a specific deadline or date.
 * Extends the {@link Task} class.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} with the specified description.
     * The task is initialized as not done.
     *
     * @param body the description of the task
     */
    public Todo(String body) {
        super(body);
    }

    /**
     * Constructs a {@code Todo} with the specified description and completion status.
     *
     * @param body the description of the task
     * @param isDone the completion status of the task
     */
    public Todo(String body, boolean isDone) {
        super(body, isDone);
    }

    /**
     * Returns a string representation of the {@code Todo}.
     * The format is "[T][status] description", where [status] is "[X]" if the task is done, or "[ ]" if it is not done.
     *
     * @return the string representation of the {@code Todo}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the {@code Todo} for saving to a file.
     * The format is "todo,[status],[description]", where [status] is "T" if the task is done, or "F" if it is not done.
     *
     * @return the string representation of the {@code Todo} for saving
     */
    @Override
    public String saveString() {
        return "todo," + super.saveString();
    }
}
