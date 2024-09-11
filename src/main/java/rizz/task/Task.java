package rizz.task;


/**
 * Represents an abstract Task that has a description (text) and a completion status (isDone).
 * This class is meant to be extended by specific types of tasks like ToD0, Event, or Deadline.
 */
public abstract class Task {
    protected final String text;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param text The description of the task.
     * @param isDone The completion status of the task. If true, the task is marked as done.
     */
    public Task(String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
    }

    /**
     * Exports the task as a formatted string for saving.
     * This method is abstract and should be implemented by subclasses.
     *
     * @return The string representation of the task for file storage.
     */
    public abstract String export();

    /**
     * Marks the task as done by setting the completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the completion status to false.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task as a string.
     * The format will be "X" if the task is done, or " " (a space) if the task is not done.
     *
     * @return A string representation of the task's completion status.
     */
    public String printStatus() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns the current completion status of the task.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description (text).
     */
    public String getText() {
        return text;
    }

    /**
     * Returns a string representation of the task.
     * The format will be: "[<status>] <text>"
     * where <status> is either "X" for done or " " for not done, and <text> is the task description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.printStatus(), this.text);
    }
}