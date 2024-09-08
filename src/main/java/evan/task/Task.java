package evan.task;

/**
 * Represents a task that the chatbot stores.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Instantiates a Task object with a given description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String representation of the Task that will be stored in the storage .txt file.
     * The exact format of the String returned varies depending on the type of Task that this method is called on.
     */
    public abstract String encodeAsString();

    /**
     * Returns a string representing whether the Task is done or undone.
     *
     * @return "X" if the Task is done, and an empty string otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the Task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns true if the Task's description contains the given description, and false otherwise.
     */
    public boolean containsMatchingDescription(String description) {
        return this.description.contains(description);
    }

    /**
     * Returns a String representation of the Task used for display purposes.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
