package lexi.task;

/**
 * Represents a task in the Lexi application.
 * A task has a name and a status indicating whether it is done or not.
 */
public class Task {
    private boolean isDone;
    private final String taskName;

    /**
     * Constructs a new Task with the specified name.
     * The task is initially not done.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
    }
    public void setDone(boolean value) {
        this.isDone = value;
    }

    /**
     * Returns whether the task is done.
     *
     * @return {@code true} if the task is done; {@code false} otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Checks if the task name contains the specified query string.
     *
     * @param query The string to search for within the task name.
     * @return {@code true} if the task name contains the query string, {@code false} otherwise.
     */
    public boolean containsQuery(String query) {
        return taskName.contains(query);
    }

    /**
     * Returns the string representation of the task.
     * The string includes the status of the task (done or not done) and the task's name.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + this.taskName;
    }
}
