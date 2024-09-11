package tasks;

/**
 * Represents a task with a description and a completion status.
 * This is an abstract class that other specific task types will extend.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String priority;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description, String priority) {
        this.description = description;
        this.priority = priority;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "1" indicates the task is done, "0" indicates it is not done.
     *
     * @return the status icon as a String
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns the priority icon of the task.
     * 🟢 for low, 🟡 for medium, 🔴 for high.
     *
     * @return the priority icon as a String
     */
    public String getPriorityIcon() {
        switch(this.priority.toLowerCase()) {
        case "high":
            return "🔴"; // High priority
            // Fallthrough
        case "medium":
            return "🟡"; // Medium priority
            // Fallthrough
        case "low":
            return "🟢"; // Low priority
            // Fallthrough
        default:
            return "🟡"; // Default to medium if not specified
            // Fallthrough
        }
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task as a String
     *
     * @return the description of the task as a string
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the priority of the task as a String
     *
     * @return the priority of the task as a string
     */
    public String getPriority() {
        return this.priority;
    }

    /**
     * Returns the string representation of the task for display purposes.
     * This method must be implemented by subclasses to provide specific details.
     *
     * @return the string representation of the task
     */
    @Override
    public abstract String toString();

    /**
     * Returns the string representation of the task suitable for saving to a file.
     * This method must be implemented by subclasses to ensure proper formatting.
     *
     * @return the string representation of the task for file storage
     */
    public abstract String toFileString();

}

