package main.java.angel;

/**
 * Represents a task with a description.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task instance with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Gets the format for saving the task.
     *
     * @return The save format string.
     */
    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Compares this task with another object to check for equality.
     * Tasks are considered equal if their descriptions and status (isDone) are the same.
     *
     * @param obj The object to compare with this task.
     * @return true if the given object is equal to this task, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Task) {
            Task otherTask = (Task) obj;
            return this.description.equals(otherTask.description);
        }
        return false;
    }
}
