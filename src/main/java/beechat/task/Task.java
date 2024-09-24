package beechat.task;

/**
 * Represents an abstract task in the Beechat chatbot application.
 * This class provides a template for all other task classes.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    /**
     * Constructs a TodoTask with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation indicating whether the task is done or not.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    } // mark task as done

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    } // mark task as undone


    /**
     * Returns the string representation of the task for saving to a file.
     *
     * @return A formatted string representation of the task in a file-friendly format.
     */
    public abstract String toSaveFormat();

    /**
     * Returns the string representation of the task.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
