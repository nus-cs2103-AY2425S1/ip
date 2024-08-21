package task;

/**
 * A task which can be completed.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Task {
    private String description;
    private boolean isComplete;

    /**
     * Constructor for a new task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        isComplete = false;
    }

    /**
     * Constructor for a new task with the specified status of the task.
     *
     * @param description the description of the task.
     * @param isComplete the status of the task.
     */
    public Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    /**
     * Return whether the task is completed.
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Mark the current task as completed.
     *
     * @return whether the tasks status has been changed.
     */
    public boolean markComplete() {
        if (isComplete) {
            return false;
        }
        isComplete = true;
        return true;
    }
    /**
     * Mark the current task as incomplete.
     *
     * @return whether the tasks status has been changed.
     */
    public boolean markIncomplete() {
        if (!isComplete) {
            return false;
        }
        isComplete = false;
        return true;
    }

    /**
     * Return the String representation of the task.
     */
    @Override
    public String toString() {
        String symbol = isComplete ? "X" : " ";
        return String.format("[%s] %s", symbol, description);
    }
}
