package David.Task;

public abstract class Task {
    private String taskName;
    private boolean completed;

    public Task(String s, boolean completed) {
        this.completed = completed;
        this.taskName = s;
    }

    /**
     * Returns the name of the event
     * @return name of the event
     */
    public String getTask() {
        return this.taskName;
    }

    /**
     * Checks if the task is completed
     * @return true if task is completed else false
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks tasks as done
     */
    public void markAsDone() {
        this.completed = true;
    }

    /**
     * Marks task as undone
     */
    public void markAsUnDone() {
        this.completed = false;
    }

    /**
     * Converts the task to a string suitable for storing into the
     * cache
     * @return Parsed string in the format specified for storage in the cache
     */
    public abstract String toCacheString();

    @Override
    public String toString() {
        String isCompleted = this.completed ? "X" : " ";
        return "[" + isCompleted + "] " + this.taskName;
    }

}
