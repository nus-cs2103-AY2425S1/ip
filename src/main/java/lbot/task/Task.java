package lbot.task;

/**
 * Represents a {@link Task}.
 */
public abstract class Task {
    protected String type;
    protected String description;
    protected boolean isComplete;

    Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    /**
     * Reverses the status of the given task.
     */
    public void mark() {
        isComplete = !isComplete;
    }

    /**
     * Returns description of task.
     *
     * @return description of task.
     */
    public String getDescription() {
        return description;
    }

    protected String status() {
        if (isComplete) {
            return "x";
        }
        return " ";
    }

    /**
     * Returns status of task.
     *
     * @return true if task is complete, false if not.
     */
    public boolean getStatus() {
        return isComplete;
    }

    @Override
    public String toString() {
        return "[" + status() + "] " + description;
    }
}
