package Gumball;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected String originalInput;

    protected String taskType;

    /**
     *
     * @param description The description of the task.
     * @param originalInput The original input that was inputted by the user.
     */
    public Task(String description,String originalInput) {
        this.description = description;
        this.originalInput = originalInput;
        this.isDone = false;
        taskType = "[ ]";
    }

    /**
     * Returns the type of Task.
     * @return The type of task.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Returns the Status icon depending on whether the task is marked as done, an "[X]" if done and a "[ ]" otherwise.
     * @return the Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns description of task.
     * @return description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the orignal input to produce task.
     * @return the orignal input to produce task.
     */
    public String getOriginalInput() {
        return originalInput;
    }

    /**
     * Marks the task as done.
     */

    public void markDone() {
        isDone = true;
    }

    /**
     * Returns true if task is marked as done and false otherwise.
     * @return true if task is marked as done and false otherwise.
     */
    public boolean isItDone() {
        return isDone;
    }


}
