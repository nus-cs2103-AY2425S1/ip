package noosy.task;

/**
 * Represents an abstract task with a name and completion status.
 */
public abstract class Task {
    /**
     * The completion status of the task.
     */
    protected boolean status;
    /**
     * The name or description of the task.
     */
    protected String name;

    /**
     * Constructs a new Task with the given description and default status (not completed).
     *
     * @param desc The description of the task.
     */
    public Task(String desc) {
        this.name = desc;
        this.status = false; // default: task not completed
    }

    /**
     * Constructs a new Task with the given description and status.
     *
     * @param desc   The description of the task.
     * @param status The initial completion status of the task.
     */
    public Task(String desc, boolean status) {
        this.name = desc;
        this.status = status;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isDone() {
        return this.status;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.status = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unDone() {
        this.status = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDesc() {
        return this.name;
    }

    /**
     * Returns a string representation of the task's status for file storage.
     *
     * @return "1" if the task is completed, "0" otherwise.
     */
    public String storeInFileAsString() {
        return String.valueOf(this.status ? 1 : 0);
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String prepend = this.status ? "[X] " : "[ ] ";
        return prepend + this.name;
    }
}
