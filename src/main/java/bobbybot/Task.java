package bobbybot;

/**
 * Represents an abstract task in the task list.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     *
     * @return Type of the task.
     */
    public abstract String getTaskType();

    /**
     * Returns a string representation of the task to be saved in the file.
     *
     * @return String representation of the task to be saved in the file.
     */
    public abstract String getFileString();
}