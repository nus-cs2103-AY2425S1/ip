package task;
import java.time.LocalDateTime;
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructs a Task with a specified description and done status.
     *
     * @param description The description of the task.
     * @param isDone      Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Constructs a Task with a specified description. The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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
    public void markAsNotDone() {
        isDone = false;
    }
    /**
     * Returns the string representation of the task.
     *
     * @return A string in the format: [status icon] description.
     * @throws AssertionError If the task description is null.
     */
    @Override
    public String toString() {
        assert description != null:"empty description";
        return "[" + getStatusIcon() + "]" + description;
    }
    /**
     * Returns a string representation of the task that is saved to a file.
     *
     * @return A string in the format: finished status (1 or 0) | description.
     * @throws AssertionError If the task description is null.
     */
    public String save() {
        assert description != null:"empty description";
        return (isDone ? "1" : "0") + " | " + description;
    }
    /**
     * Returns the date and time associated with the task, if applicable.
     *
     * @return The date and time for the task, or null if not applicable.
     */
    public LocalDateTime getDateTime() {
        return null;
    }
}
