package trackie.tasks;

/**
 * Represents an abstract task.
 */
public abstract class Task {
    protected String description;
    protected boolean completed;

    /**
     * Creates a task.
     *
     * @param description the description supplied by the user.
     */
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Creates a task with the specified completion status.
     *
     * @param description the description supplied by the user.
     * @param status the completion status.
     */
    public Task(String description, int status) {
        this.description = description;
        if (status == 1) {
            this.completed = true;
        } else {
            this.completed = false;
        }
    }

    /**
     * Generates the status icon for the task, depending on the completion status.
     *
     * @return "X" if completed, " " if not completed.
     */
    public String getStatusIcon() {
        return completed ? "X" : " ";
    }

    /**
     * Retrieves the description of the task.
     *
     * @return A String containing the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.completed = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markUndone() {
        this.completed = false;
    }

    /**
     * Retrieves the relevant information about a task.
     * This method must be implemented by subclasses.
     *
     * @return A String containing relevant information about the task,
     *         depending on the type of the task.
     */
    @Override
    public abstract String toString();

    /**
     * Retrieves the type of the task.
     * This method must be implemented by subclasses.
     *
     * @return A String denoting the type of the task.
     */
    public abstract String getTaskType();


}
