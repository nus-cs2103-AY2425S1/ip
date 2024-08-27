package mahesh.task;

/**
 * Represents a task with a description and a completion status.
 */
public abstract class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task.
     */
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String indicator = this.isDone ? "[X]" : "[ ]";
        return indicator + " " + this.description;
    }

    /**
     * Converts the task to a string format suitable for saving to a file.
     * The format includes the completion status and the description of the task.
     *
     * @return A string representation of the task for file storage.
     */
    public String toFileEntry() {
        return isDone + "/" + description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }
}
