package nuffle.task;


/**
 * Represents an abstract task.
 * This class provides a foundation for different types of tasks with common functionalities
 * such as marking the task as done, getting its status, and converting it to a string representation.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the specified description.
     * By default, the task is marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task as a string, indicating whether the task is done or not.
     *
     * @return "X" if the task is done, otherwise an empty space " ".
     */
    public String getStatusInfo() {
        return (isDone ? "X" : " ");
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
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task,
     * including its status (done or not done) and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String mark = getStatusInfo();
        return "[" + mark + "] " + description;
    }

    /**
     * Converts the task to a specific string format for saving to a file.
     * The format will vary depending on the specific type of task.
     *
     * @return A string representation of the task suitable for saving to a file.
     */
    public abstract String printSaveFormat();

}

