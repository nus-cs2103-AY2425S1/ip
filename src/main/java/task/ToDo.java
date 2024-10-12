package task;

/**
 * Represents a To-Do task, which is a basic task with only a description and no time constraints.
 */
public class ToDo extends Task {

    /**
     * Creates new ToDo task.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Creates new ToDo task with option to mark as done.
     *
     * @param description Description of the task.
     * @param isDone True if task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }

    /**
     * Returns string representation of the task.
     *
     * @return Task details as string.
     */
    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description;
    }
}
