package fanny.task;

/**
 * Represents a general task with description and completion status of the task.
 */
public class Task {

    /** The description of the task. */
    protected String description;
    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs an {@code Task} with the specified description.
     *
     * @param description A description of the general task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the completion status of the task.
     *
     * @return A string representing the completion status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks this task as done by setting the {@code isDone} status to {@code true}.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done by setting the {@code isDone} status to {@code false}.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the description of the task.
     *
     * @return A string representing the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task, including its status,
     * and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
