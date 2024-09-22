package voidcat.task;

/**
 * Represents a task in the Void Cat program.
 * A task has a description, and can be marked as done or not done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    /**
     * Constructs a task with the specified description.
     *
     * @param description The description of the task.
     */
    protected Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done (sets it as not done).
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task ("[X]" for done or "[ ]" for not done).
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[  ]"); // if task is done, mark with X
    }

    /**
     * Returns the task type identifier (e.g., "T" for ToDo, "D" for Deadline, "E" for Event).
     *
     * @return The task type identifier.
     */
    public abstract String getTaskType();

    /**
     * Returns a formatted string representing the task to save to a file.
     *
     * @return The string in the save format.
     */
    public String toSaveFormat() {
        return String.format("%s | %d | %s", this.getTaskType(), (isDone ? 1 : 0), description);
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getStatusIcon() + " " + this.description;
    }

}
