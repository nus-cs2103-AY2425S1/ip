package killua.task;

/**
 * Represents a generic task in the Killua application.
 * This class provides basic functionalities for any type of task, including description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of this task.
     * The icon is 'X' if the task is done, otherwise a space character.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the task description contains the specified keyword.
     * This method is used to determine if a task matches the search criteria based on a keyword.
     *
     * @param keyword The keyword to search for in the task description.
     * @return true if the task description contains the keyword, false otherwise.
     */
    public boolean isMatched(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns a string representation of this task in a format suitable for saving to a file.
     * The format includes the completion status and description.
     *
     * @return A string representation of the task for saving.
     */
    public String toSave() {
        return " | " + (isDone ? 1 : 0) + " | " + description;
    }

    /**
     * Returns a string representation of this task in a user-friendly format.
     * The format includes the status icon and description.
     *
     * @return A string representation of the task for display.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
