package task;

/**
 * The {@code Task} class is an abstract base class that represents a generic task.
 * It provides common properties and methods for all tasks, including a name, description, and completion status.
 * Subclasses of {@code Task} should provide specific implementations for different types of tasks.
 */
public abstract class Task {
    protected String name;
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified name and description.
     * By default, the task is not marked as done.
     *
     * @param name        The name of the task.
     * @param description The description of the task.
     */
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is marked as done; {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
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
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status, name, and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s\n\tDescription: %s", isDone ? "X" : " ", name, description);
    }
}
