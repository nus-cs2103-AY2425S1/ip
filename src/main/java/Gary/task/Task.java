package Gary.task;

/**
 * Represents a task in the application. Each task has a description and a status indicating
 * whether the task is completed or not.
 */
public abstract class Task {
    // Description of the task
    protected String description;
    // Completion status of the task
    protected boolean isDone = false;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Toggles the completion status of the task.
     */
    public void editStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Converts the task to a string that can be written to a file.
     *
     * @return A formatted string representation of the task for saving to a file.
     */
    public abstract String parseToFile();

    /**
     * Returns a string representation of the {@code Task}.
     *
     * @return A string representation of the {@code Task}.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }

    /**
     * Checks if this {@code Task} is equal to another object.
     * Two tasks are considered equal if they have the same description and completion status.
     *
     * @param obj The object to compare with this {@code Task}.
     * @return {@code true} if the specified object is equal to this {@code Task}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Task) {
            Task otherTask = (Task) obj;
            return this.description.equals(otherTask.description) && this.isDone == otherTask.isDone;
        }
        return false;
    }
}

