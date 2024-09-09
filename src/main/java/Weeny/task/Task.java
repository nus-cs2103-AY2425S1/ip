package weeny.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    public final String WRITE_TIME_PATTERN = "H:mm a";
    public final String WRITE_DATE_PATTERN = "MMM d yyyy";
    public final String READ_TIME_PATTERN = "HHmm";
    public final String READ_DATE_PATTERN = "dd/MM/yyyy";
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Creates a new task with the specified description and type.
     *
     * @param description The description of the task.
     * @param type The type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns a boolean representing if a keyword is found
     *
     * @param keyWord
     * @return True if the keyword is found in the description
     */
    public boolean containString(String keyWord) {
        if (this.description.contains(keyWord)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "X" if the task is done, otherwise a space.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task for output.
     *
     * @return A string with task details.
     */
    public String toOutput() {
        return " ";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format "[statusIcon] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
