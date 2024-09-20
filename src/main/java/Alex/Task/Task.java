package Alex.Task;

/**
 * Abstract base class for all tasks.
 */
public abstract class Task {
    public boolean isDone;
    protected String description;


    /**
     * Constructs a Task with the given description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the type of the task.
     * @return The type of the task.
     */
    public abstract TaskType getTaskType();

    /**
     * Gets the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     * @return True if the task is done, otherwise false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }
}

