package cookie.task;
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new {@code Task} with the specified completion status and description.
     *
     * @param isDone a boolean indicating whether the task is completed
     * @param description the description of the task
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status of the task.
     *
     * @return "X" if the task is done, "" (an empty string) if the task is not done
     */
    public String getStatus() {
        return isDone ? "X" : "";
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }
}
