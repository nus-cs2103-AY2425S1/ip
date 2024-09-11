package vecrosen;

/**
 * The basic task type, known as a todo if used as-is.
 * Has of a description and tracks whether it has been marked as complete by the user.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Initializes a Task object. Starts marked as incomplete.
     *
     * @param description The description the task will have.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the completion state of the task.
     *
     * @param nv the value to set isDone to
     */
    public void setDone(boolean nv) {
        isDone = nv;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is marked as complete.
     *
     * @return The completion state of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        char charDone = ' ';
        if (isDone) {
            charDone = 'X';
        }
        return "[T][" + charDone + "] " + description;
    }
}
