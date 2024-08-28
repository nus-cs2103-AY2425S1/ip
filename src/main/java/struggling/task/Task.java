package struggling.task;

public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a Task object with description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a formatted string representation of Task for display.
     */
    @Override
    public String toString() {
        char label = this.isDone ? 'X' : ' ';
        return String.format("[%s] %s", label, this.description);
    }

    /**
     * Mark a Task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark a Task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a formatted string of Task for Storage.
     */
    public String getState() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    public String getDescription() {
        return this.description;
    }
}
