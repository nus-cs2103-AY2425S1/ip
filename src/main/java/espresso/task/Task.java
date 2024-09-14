package espresso.task;

/**
 * Represents a generic task with a description and a status indicating whether the task
 * is completed or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * Task is initially unmarked.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task description contains the specified substring.
     *
     * @param substring The substring to search for in the task description.
     * @return True if the description contains the substring, otherwise false.
     */
    public boolean contains(String substring) {
        return description.contains(substring);
    }

    /**
     * Returns the status icon of the task. "X" if the task is marked done,
     * and an empty space otherwise.
     *
     * @return The status icon representing whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task formatted for saving to a text file.
     * The format includes whether the task is done (1 for done, 0 for not done)
     * and the description of the task too.
     *
     * @return A string representing the task for saving to a text file.
     */
    public String toText() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}