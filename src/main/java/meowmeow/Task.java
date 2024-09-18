package meowmeow;

/**
 * Represents a task which can be marked as done or undone
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * Initially, the task is not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if the Task is marked as done.
     * Returns " " if the Task is not marked as done.
     *
     * @return Status icon of whether the Task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string containing the status icon and task description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }


    /**
     * Returns the String representation of a task for storing in the save file.
     *
     * @return String representation of task for storing in save file.
     */
    public String convertToFileFormat() {
        return (this instanceof ToDo ? "T" : this instanceof Deadline ? "D" : "E") + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }
}
