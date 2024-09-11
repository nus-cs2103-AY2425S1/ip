package MeowMeow;

/**
 * Represents a task which can be marked as done or undone
 */
public class Task {
    protected String description;
    protected boolean isDone;

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

    public void markDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the String representation of a task for storing in the save file.
     *
     * @return String representation of task for storing in save file.
     */
    public String toFileFormat() {
        return (this instanceof ToDo ? "T" : this instanceof Deadline ? "D" : "E") + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
