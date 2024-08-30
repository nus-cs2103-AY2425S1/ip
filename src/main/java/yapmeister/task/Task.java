package yapmeister.task;

/**
 * Represents a Task with a name
 * @author BlazeChron
 */
public abstract class Task {
    private final String taskName;
    private boolean isCompleted;

    /**
     * Creates a Task
     * @param taskName Name of the Task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }
    public String getTaskName() {
        return taskName;
    }

    /**
     * Exports String representation to file storage format.
     * @return String representation in file storage format.
     */
    abstract public String exportString();

    /**
     * Returns String format of the Task in YapMeister's required format.
     * @return String format for YapMeister use.
     */
    @Override
    public String toString() {
        String x;
        if (this.isCompleted) {
            x = "X";
        } else {
            x = " ";
        }
        return String.format("[%s] %s", x, this.taskName);
    }
}
