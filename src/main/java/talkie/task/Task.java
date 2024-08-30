package talkie.task;

/**
 * Represents a task in the Talkie application.
 * <p>
 * {@code Task} is an abstract class that defines the common attributes and methods for all types of tasks.
 * It provides functionality to manage the status and description of a task, and requires subclasses to
 * implement the {@code stringifyTask} method to specify how the task should be serialized.
 * </p>
 */
public abstract class Task {
    protected String desc;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     * <p>
     * The task is initially marked as not done.
     * </p>
     *
     * @param desc The description of the task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * <p>
     * The string includes the task's status icon (either "[X]" for done or "[ ]" for not done) and its description.
     * </p>
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.desc;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is done; {@code false} otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the status icon of the task.
     * <p>
     * The icon is "[X]" if the task is done, or "[ ]" if the task is not done.
     * </p>
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as done.
     * <p>
     * This method sets the task's status to done.
     * </p>
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     * <p>
     * This method sets the task's status to not done.
     * </p>
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Serializes the task to a string format.
     * <p>
     * This is an abstract method that must be implemented by subclasses to define how the task should be converted
     * to a string representation for storage or other purposes.
     * </p>
     *
     * @return A string representation of the task.
     */
    public abstract String stringifyTask();
}
