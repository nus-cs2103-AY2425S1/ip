public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns whether the task is done or not
     *
     * @return status of task
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns description of task
     *
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Indicates that the task is done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Indicates that the task is not done
     */
    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
