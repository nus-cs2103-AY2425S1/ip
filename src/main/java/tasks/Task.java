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
     * "1" indicates the task is done, " " indicates it is not done.
     *
     * @return the status icon as a String
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns the status icon of the task.
     * "✔️" indicates the task is done, " " indicates it is not done.
     *
     * @return the status icon as a String
     */
    public String getStatusIcon() {
        return (isDone ? "✔" : " ");
    }

    /**
     * Returns the priority icon of the task.
     * Uses different icons for macOS and Windows.
     *
     * @return the priority icon as a String
     */
    public String getPriorityIcon() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            // macOS emoji icons
            switch(this.priority.toLowerCase()) {
            case "high":
                return "🔴"; // High priority
            case "medium":
                return "🟡"; // Medium priority
            case "low":
                return "🟢"; // Low priority
            default:
                return "🟡"; // Default to medium if not specified
            }
        } else {
            // other os emoji fallback
            switch(this.priority.toLowerCase()) {
            case "high":
                return "[HIGH]"; // High priority
            case "medium":
                return "[MED]";  // Medium priority
            case "low":
                return "[LOW]";  // Low priority
            default:
                return "[MED]";  // Default to medium if not specified
            }
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

