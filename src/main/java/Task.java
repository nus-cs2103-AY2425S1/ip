/**
 * Represents a task with a description and status (done or not done).
 * This is an abstract class to be extended by specific task types
 * such as ToDos, Deadlines, and Events.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     *
     * @throws ReginaException If the task is already marked as done.
     */
    public void checkTask() throws ReginaException {
        if (isDone) {
            throw new ReginaException("Task is already marked!");
        }
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting it back to not done.
     *
     * @throws ReginaException If the task is already unmarked.
     */
    public void uncheckTask() throws ReginaException {
        if (!isDone) {
            throw new ReginaException("Task is already unmarked!");
        }
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the task's completion status.
     *
     * @return "X" if the task is done, or " " if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representing the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
