package Cookie;
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task class.
     * Cannot be instantiated.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Another constructor for Task class.
     * Cannot be instantiated.
     *
     * @param isDone Whether the task is done or not.
     * @param description Description of task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status of the task.
     *
     * @return "X" if done, "" if not done.
     */
    public String getStatus() {
        return isDone ? "X" : "";
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }
}
