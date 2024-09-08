package david.task;

/**
 * Abstract task class
 */
public abstract class Task {
    private String taskName;
    private boolean isCompleted;

    /**
     * constructor for Task class
     */
    public Task(String s, boolean isCompleted) {
        assert s.length() != 0 : "No event string provided";
        this.isCompleted = isCompleted;
        this.taskName = s;
    }

    /**
     * Returns the name of the event
     * @return name of the event
     */
    public String getTask() {
        assert taskName.length() != 0 : "No event string provided";
        return this.taskName;
    }

    /**
     * Checks if the task is completed
     * @return true if task is completed else false
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Marks tasks as done
     */
    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Marks task as undone
     */
    public void markAsUnDone() {
        this.isCompleted = false;
    }

    /**
     * Converts the task to a string suitable for storing into the
     * cache
     * @return Parsed string in the format specified for storage in the cache
     */
    public abstract String toCacheString();

    @Override
    public String toString() {
        assert taskName.length() != 0 : "No event string provided";
        String isCompleted = this.isCompleted ? "X" : " ";

        return "[" + isCompleted + "] " + this.taskName;
    }

}
