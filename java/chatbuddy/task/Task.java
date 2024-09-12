package chatbuddy.task;

/**
 * Represents a task in the ChatBuddy task list.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     *                    Must not be null or empty.
     *
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        assert !description.trim().isEmpty() : "Task description should not be empty";

        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon (done or not done) of the task.
     *
     * @return The status icon of the task ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        assert isDone == true || isDone == false : "Task status should be either true or false";

        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        assert !isDone : "Task is already marked as done";

        this.isDone = true;
    }

    /**
     * Unmarks the task (sets it as not done).
     */
    public void unmarkAsDone() {
        assert isDone : "Task is already not done";

        this.isDone = false;
    }

    @Override
    public String toString() {
        assert description != null : "Task description should not be null";

        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the file format representation of the task.
     *
     * @return A string representing the task in file format.
     */
    public abstract String toFileFormat();
}
