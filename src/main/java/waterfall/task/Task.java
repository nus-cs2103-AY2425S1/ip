package waterfall.task;

/**
 * Represents a task in the waterfall application.
 *
 * @author Wai Hong
 */
public abstract class Task {
    private boolean isDone;
    private final String title;

    /**
     * Constructs a Task object with the specified title.
     *
     * @param title Title of the task.
     */
    public Task(String title) {
        this.isDone = false;
        this.title = title;
    }

    /**
     * Provides the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Provides the status of the task.
     *
     * @return The status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the task status to the specified status.
     *
     * @param done A boolean to indicate done or not done.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + title;
    }

    /**
     * Provides the storage string of the task.
     *
     * @return the storage string of the task.
     */
    public abstract String toStorageString();
}
