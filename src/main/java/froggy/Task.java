package froggy;

/**
 * The {@code Task} class represents a task with a description and a status indicating
 * whether it is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     * If the task is done, returns "X", otherwise returns a space.
     *
     * @return The status icon, "X" if done, otherwise a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the status of the task to either done or not done.
     *
     * @param newStatus A boolean indicating the new status of the task.
     *                  {@code true} for done, {@code false} for not done.
     */
    public void setStatus(boolean newStatus) {
        this.isDone = newStatus;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the task in a format suitable for storing in a text file.
     * The status is represented as "1" if done, otherwise "0".
     *
     * @return A string representation of the task for storage in a text file.
     */
    public String toTxt() {
        return (isDone ? "1 " : "0 ") + this.description;
    }

    /**
     * Compares this task with another task for equality.
     * Two tasks are considered equal if their descriptions are the same.
     *
     * @param task The task to compare with this task.
     * @return {@code true} if the tasks are equal, {@code false} otherwise.
     */
    public boolean equals(Task task) {
        if (this == task) {
            return true;
        }
        if (task == null || getClass() != task.getClass()) {
            return false;
        }
        return description.equals(task.getDescription());
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
