package bobbybot.tasks;

/**
 * Represents an abstract task in the task list.
 */
public abstract class Task implements Cloneable {

    private final String description;
    private boolean isDone;

    /**
     * Creates a new Task object.
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
     * Returns a string representation of the task to be saved in the file.
     *
     * @return String representation of the task to be saved in the file.
     */
    public abstract String getFileString();

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // It should never reach here
            e.printStackTrace();
        }
        return null;
    }
}
