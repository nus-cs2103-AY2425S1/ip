/**
 * A class to depict a task which can be completed.
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

    /** Returns true if the task has been completed, false otherwise. */
    public boolean isComplete() {
        return this.isCompleted;
    }

    /**
     * Mark the current task as completed.
     *
     * @return whether the tasks status has been changed.
     */
    public boolean markComplete() {
        if (this.isCompleted) {
            return false;
        }
        this.isCompleted = true;
        return true;
    }
    /**
     * Mark the current task as incomplete.
     *
     * @return whether the tasks status has been changed.
     */
    public boolean markIncomplete() {
        if (!this.isCompleted) {
            return false;
        }
        this.isCompleted = false;
        return true;
    }

    /** Returns the String representation of the task. */
    @Override
    public String toString() {
        return this.description;
    }
}
