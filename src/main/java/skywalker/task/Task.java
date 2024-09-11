package skywalker.task;

/**
 * Represents a specific type of task that has a status indicating whether it is done.
 */
public class Task {
    protected TaskType taskType;
    private String description;
    private boolean isDone;


    /**
     * Constructs a Task with the specified description and task type.
     * The task is initialized to be not done.
     *
     * @param description The description of the task.
     * @param taskType    The type of the task.
     */
    public Task(String description, TaskType taskType) {
        assert description != null : "Task description should not be null";
        assert !description.isBlank() : "Task description should not be empty";
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the status of the task.
     *
     * @param done True to mark the task as done, false to mark it as not done.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns a 'X' if the task is done and an empty space otherwise.
     *
     * @return "X" if the task is done, otherwise a space character " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        if (!this.isDone) {
            this.isDone = true;
        } else {
            System.out.println("The task is already done.");
        }
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        if (this.isDone) {
            this.isDone = false;
        } else {
            System.out.println("The task is already not done.");
        }
    }



    /**
     * Returns a string representation of the task, including its status icon
     * and description.
     *
     * @return A string representation of the task in the format "[X] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
