package task;

/**
 * A task which can be completed.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Task {
    private String description;
    private boolean isCompleted;

    /**
     * Constructor for a new task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Return whether the task is completed.
     */
    public boolean isComplete() {
        return isCompleted;
    }

    /**
     * Mark the current task as completed.
     *
     * @return whether the tasks status has been changed.
     */
    public boolean markComplete() {
        if (isCompleted) {
            return false;
        }
        isCompleted = true;
        return true;
    }
    /**
     * Mark the current task as incomplete.
     *
     * @return whether the tasks status has been changed.
     */
    public boolean markIncomplete() {
        if (!isCompleted) {
            return false;
        }
        isCompleted = false;
        return true;
    }

    /**
     * Return the String representation of the task.
     */
    @Override
    public String toString() {
        String symbol = isCompleted ? "X" : " ";
        return String.format("[%s] %s", symbol, description);
    }
}
