package atreides.task;

/**
 * represents the tasks that the user can add into the list
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this("");
    }

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * represents whether the task has been completed or not
     * @param trueString
     * @param falseString
     * @return trueString if completed else falseString
     */
    public String getStatusIcon(String trueString, String falseString) {
        return (isDone ? trueString : falseString); // mark done task with X
    }

    /**
     * marks the task as completed or undone based on the boolean var
     * @param var
     */
    public void markDone(Boolean var) {
        this.isDone = var;
    }

    /**
     *
     * @return string to write into the .txt file for storage and subsequent reading
     */
    public String write() {
        return " | " + this.getStatusIcon("1", "0") + " | " + this.description;
    }

    /**
     *
     * @return the representation of the task and whether its completed
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon("X", " ") + "] " + this.description;
    }
}