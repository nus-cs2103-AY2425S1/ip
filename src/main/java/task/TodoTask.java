package task;

import utility.Tag;

/**
 * A Todo Task is a more specific implementation of {@link Task}.
 */
public class TodoTask extends Task {

    /**
     * {@inheritDoc}
     */
    public TodoTask(String taskDescription, Tag taskTag) {
        super(taskDescription, taskTag);
    }

    private TodoTask(boolean isDone, String taskDescription, Tag taskTag) {
        super(isDone, taskDescription, taskTag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task markAsDone() {
        return super.isDone
            ? this
            : new TodoTask(true, super.taskDescription, taskTag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task markAsUndone() {
        return super.isDone
            ? new TodoTask(super.taskDescription, this.taskTag)
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
