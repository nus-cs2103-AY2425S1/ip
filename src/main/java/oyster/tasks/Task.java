package oyster.tasks;

/**
 * Abstract Task class for TaskList.
 */
public abstract class Task {
    private boolean isComplete = false;
    private String description;

    /**
     * Creates a Task given its values.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        assert description != null;

        this.description = description;
    }

    /**
     * Marks the Task as complete.
     */
    public void mark() {
        isComplete = true;
    }

    /**
     * Unmarks the Task as not complete.
     */
    public void unmark() {
        isComplete = false;
    }

    public boolean isMarked() {
        return isComplete;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + (isComplete ? "X" : " ") + "] " + description;
    }

    /**
     * @return A writable save String of the Task.
     */
    public abstract String[] compose();
}
