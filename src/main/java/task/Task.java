package task;

/**
 * The {@code Task} class represents a generic task with a description and a done status.
 * It provides basic functionalities such as marking a task as done or not done,
 * and retrieving the task's type, status, and description.
 */
public class Task {

    private final String DESC;
    private boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description.
     * By default, the task is initially not marked as done.
     *
     * @param desc The description of the task.
     */
    public Task(String desc) {
        this.DESC = desc;
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting the {@code isDone} flag to {@code true}.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the {@code isDone} flag to {@code false}.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the type of the task as a string. This method can be overridden by subclasses.
     * By default, it returns "[ ]", indicating a generic task.
     *
     * @return A string representing the type of task.
     */
    public String getType() {
        return "[ ]";
    }

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public Boolean getDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return A string representing the description of the task.
     */
    public String getDesc() {
        return this.DESC;
    }

    /**
     * Returns a string representation of the task, including its type,
     * completion status, and description.
     *
     * @return A string representing the task, in the format "[type][status] description".
     */
    @Override
    public String toString() {
        return getType() + (this.isDone ? "[X] " : "[ ] ") + this.DESC;
    }
}
