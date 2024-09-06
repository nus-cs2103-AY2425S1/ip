package vinegar.task;

/**
 * Represents a task in the Vinegar task management application.
 * <p>
 * This is an abstract class that contains common properties and methods for all task types
 * such as Todos, Deadlines, and Events. Each task has a description and a status indicating
 * whether the task is completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * By default, the task is not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     * If the task is completed, it returns "X"; otherwise, it returns a space.
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string in the format "[status] description".
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task to a format suitable for saving to a file.
     * The exact format is defined in subclasses that represent specific types of tasks.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFileFormat();
}
