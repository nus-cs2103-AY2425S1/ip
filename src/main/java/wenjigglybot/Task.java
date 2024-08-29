package wenjigglybot;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@link Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return A string representing the status icon. "[X]" if done, "[ ]" if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmarkTask() {
        isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     * Subclasses can override this to return more specific task types.
     *
     * @return A string representing the task type. Default is "Normal task".
     */
    public String taskType() {
        return "Normal task";
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), description);
    }
}