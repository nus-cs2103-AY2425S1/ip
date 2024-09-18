package donk.task;

/**
 * Parent class of deadline, event, todo
 */
public abstract class Task {
    protected String description;

    protected String taskType;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     * Initializes the task with the given description and task type, and sets its completion status to false.
     * @param description The description of the task.
     * @param taskType The type of task (e.g., "T" for Todo, "D" for Deadline, "E" for Event).
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the completion status of the task.
     *
     * @return "X" if the task is completed, otherwise a space character (" ").
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the type of task.
     *
     * @return The task type as a string (e.g., "T", "D", "E").
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, otherwise false.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return A string in the format "[statusIcon] description", where "statusIcon" indicates
     *         the task's completion status and "description" is the task description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public abstract String getIsoDate();

    /**
     * Returns task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Abstract method to be implemented by subclasses to return a string representation
     * of the task suitable for saving to a file.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFileSaveString();

    public abstract int compareTo(Task task);


}
