package rapgod.tasks;

/**
 * Represents a task with a description and a completion status.
 * A task has a description that details what needs to be done and a status indicating whether it has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description and initializes its completion status to false.
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
     * @param done {@code true} if the task is completed; {@code false} otherwise.
     */
    public void setIsDone(boolean done) {
        this.isDone = done;
    }


    /**
     * Checks if the task is marked as completed.
     *
     * @return {@code true} if the task is completed; {@code false} otherwise.
     */
    public boolean isMarkedDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task, showing its completion status and description.
     * The status is represented by "[X]" if the task is done, or "[ ]" if it is not.
     *
     * @return A string in the format "[status] description" where status is either "X" or a space.
     */
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return String.format("[%s] %s", mark, this.description);
    }
}
