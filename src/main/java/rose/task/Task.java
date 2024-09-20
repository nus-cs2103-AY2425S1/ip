package rose.task;

/**
 * Represents a task.
 * A <code>Task</code> object is represented by the description of the task, the status of completion, and an
 * optional tag.
 * e.g., <code>[X] read book</code>.
 */
public class Task {
    protected final String taskName;
    protected boolean isDone;
    protected String tag;

    Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.tag = "";
    }

    Task(String taskName, boolean isDone, String tag) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.tag = tag;
    }

    Task(String taskName, String tag) {
        this.taskName = taskName;
        this.isDone = false;
        this.tag = tag;
    }

    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", taskName);
    }

    /**
     * Returns a string representation of the task in a comma-separated format.
     *
     * <p>The format is: <code>"status,taskName"</code>, where <code>status</code> is "X"
     * if the task is done, and a space (" ") if the task is not done.</p>
     *
     * @return A comma-separated string representing the task's status and name.
     */
    public String commaString() {
        return String.format("%s,%s", this.isDone ? "X" : " ", taskName);
    }

    /**
     * Marks the task as done.
     *
     * <p>This method sets the task's status to done, which is represented by
     * "X" in the string representations of the task.</p>
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     *
     * <p>This method sets the task's status to not done, which is represented by
     * a space (" ") in the string representations of the task.</p>
     */
    public void unmark() {
        this.isDone = false;
    }

    public String getTag() {
        return this.tag;
    }

    public boolean hasWord(String keyword) {
        return this.taskName.toLowerCase().contains(keyword.toLowerCase());
    }
}
