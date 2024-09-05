package Gary.task;

/**
 * The {@code Task} class represents a generic task with a description and completion status.
 * It is an abstract class that should be extended by specific types of tasks.
 */
public abstract class Task {
    protected String description;  // Description of the task
    protected boolean isDone = false;  // Completion status of the task

    /**
     * Constructs a {@code Task} object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the task.
     *
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
     * Converts the {@code Task} object into a string format suitable for saving to a file.
     *
     * @return A string representation of the {@code Task} for file storage.
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
