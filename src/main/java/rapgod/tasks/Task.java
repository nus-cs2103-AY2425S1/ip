package rapgod.tasks;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done The completion status to set.
     */
    public void setIsDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return String.format("[%s] %s", mark, this.description);
    }

}
