package murphy.task;

import murphy.MurphyException;

/**
 * An abstract class which is inherited by other forms of Tasks.
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with a description.
     * @throws MurphyException If description is empty.
     */
    public Task(String description) throws MurphyException {
        String trimmed = description.trim();
        if (trimmed.isEmpty()) {
            throw new MurphyException("Description for task cannot be empty!");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new Task with a description and specified completion status.
     * @throws MurphyException If description is empty.
     */
    public Task(String description, boolean isDone) throws MurphyException {
        String trimmed = description.trim();
        if (trimmed.isEmpty()) {
            throw new MurphyException("Description for task cannot be empty!");
        }
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns "X" if the task is done and " " otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    /**
     * Returns a String representation of the task to be stored in the save file.
     */
    public String toSaveString() {
        return String.valueOf(this.isDone) + "|" + this.description;
    }
}