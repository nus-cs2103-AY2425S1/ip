package duke;

/**
 * Represents a task with a description and a status indicating whether it is done or not.
 */
public abstract class Task {

    protected boolean isDone;
    protected String description;
    protected int priority;

    /**
     * Constructs a Task with the specified description. The task is initially not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = 0;
    }

    /**
     * Returns a string representation of the task, including its priority, status icon and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        String priority;
        String withoutPriority = this.getStatusIcon() + " " + this.description;
        if (this.priority == 1) {
            priority = "(HIGH PRIORITY)";
        } else {
            priority = "";
        }
        return priority + withoutPriority;
    }

    /**
     * Returns the status icon representing whether the task is done or not.
     *
     * @return the status icon "[X]" if the task is done, "[ ]" otherwise
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
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
    public void unmarkAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done or not.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as high priority by setting its priority to 1.
     */
    public void markAsHighPriority() {
        this.priority = 1;
    }

    /**
     * Unmarks the task as high priority by setting its priority to 0.
     */
    public void unmarkAsHighPriority() {
        this.priority = 0;
    }

    /**
     * Returns the priority of the task.
     *
     * @return the priority of the task, where 1 indicates high priority and 0 indicates no priority.
     */
    public int getPriority() {
        return this.priority;
    }

}
