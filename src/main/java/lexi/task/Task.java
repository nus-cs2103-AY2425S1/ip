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
        // Precondition: Ensure taskName is not null or empty
        assert taskName != null && !taskName.isEmpty() : "Task name cannot be null or empty.";

        this.isDone = false;
        this.taskName = taskName;
    }

    /**
     * Sets the done status of the task.
     *
     * @param value {@code true} to mark the task as done, {@code false} to mark it as not done.
     */
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
     * Checks if the task name contains a word that exactly matches the specified query string.
     * Only full words will be matched, e.g., "Han" will not match "Hans".
     *
     * @param query The string to search for as a whole word within the task name.
     * @return {@code true} if the task name contains a word that exactly matches the query, {@code false} otherwise.
     */
    public boolean containsExactWord(String query) {
        // Precondition: Ensure query is not null or empty
        assert query != null && !query.isEmpty() : "Query cannot be null or empty.";

        String pattern = "\\b" + query + "\\b";
        return taskName.matches(".*" + pattern + ".*");
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
