package slave.task;

/**
 * Task is an abstract class which contains the name of the task and
 * the state of the task, whether it was completed or not.
 */
public abstract class Task {
    private boolean isCompleted;
    private String task;

    /**
     * Creates a task of name task.
     *
     * @param task is the name of the task.
     */
    public Task(String task) {
        this.isCompleted = false;
        this.task = task;
    }

    protected Task(String task, boolean completed) {
        this.task = task;
        this.isCompleted = completed;
    }

    /**
     * @return whether the task is completed
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * @return the string description of the task.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Sets teh task as completed.
     *
     * @return the task itself to enable method chaining.
     */
    public Task setAsCompleted() {
        this.isCompleted = true;
        return this;
    }

    /**
     * Sets the task as incomplete.
     *
     * @return the task itself to enable method chaining.
     */
    public Task setAsIncomplete() {
        this.isCompleted = false;
        return this;
    }


    /**
     * Prints the task in the required format.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isCompleted()) {
            sb.append("[X]");
        } else {
            sb.append("[]");
        }
        sb.append(getTask());
        return sb.toString();
    }

    /**
     * Used as a .toString() used only when save() is called in Slave.java
     * Prints the Task's date in the format yyyy-mm-dd.
     * Formatted as such:
     * (Task type),(isCompleted),(isRecurring),(Task name),(additional class specific parameters)
     *
     * @return a String representation of the task.
     */
    public abstract String saveFormat();
}
