package bestie.task;

/**
 * Represents an abstract Task class that contains general information about Event, Deadline and Todo tasks.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with its description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if task has been completed, and an empty string has not been completed.
     * These will appear at the start of each task.
     *
     * @return X if task is completed and an empty string if task is not yet completed
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks a task as completed.
     */
    public void markTaskDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as incomplete.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the task in the format it should be saved in the bestie.txt file.
     *
     * @return correctly formatted task that will be saved in bestie.txt.
     */
    public abstract String toSaveFormat();

    public String getDescription() {
        return this.description;
    }


    /**
     * Returns string representation of the task in the console, together with its completion status.
     * Task is preceded by [X] if it is completed and [ ] if it is not yet completed.
     *
     * @return string representation of task in the console.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
