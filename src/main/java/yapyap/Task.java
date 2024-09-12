package yapyap;

/**
 * Represents a task with a description and a completion status.
 * This class serves as a base class for more specific types of tasks such as Todos, Deadlines, and Events.
 */
public class Task {
    protected String description;
    protected Boolean isDone;

    /**
     * Creates a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon representing the task's completion status.
     *
     * @return "X" if the task is done, otherwise a space character.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the task to a format suitable for saving to a file.
     * The format includes the task type, status, and description.
     *
     * @return A formatted string representation of the task for saving.
     */
    public String toSaveFormat() {
        return "T | " + (this.isDone ? 1 : 0) + " | " + this.description;
    }
}
