package task;

/**
 * A Todo Task is a more specific implementation of {@link Task}.
 */
public class TodoTask extends Task {

    /**
     * {@inheritDoc}
     */
    public TodoTask(String taskDescription) {
        super(taskDescription);
    }

    private TodoTask(boolean isDone, String taskDescription) {
        super(isDone, taskDescription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task markAsDone() {
        return super.isDone
            ? this
            : new TodoTask(true, super.taskDescription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task markAsUndone() {
        return super.isDone
            ? new TodoTask(super.taskDescription)
            : this;
    }

    /**
     * {@inheritDoc}
     * Additionally, {@code "[T]"} is prepended to the string representation to
     * indicate this is a Todo Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
