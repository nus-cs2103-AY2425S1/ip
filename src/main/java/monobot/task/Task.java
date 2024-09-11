package monobot.task;

/**
 * Represents a Task that has a description and completion status
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description and not done status.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets a Task as completed.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Sets a Task as not completed.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        String str = this.isDone ? "[X]" : "[ ]";
        str += " " + this.description;
        return str;
    }
}
