public class Task {
    private String description;
    private boolean isCompleted;

    /**
     * Constructor for a new task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /** Mark the current task as completed. */
    public void complete() {
        this.isCompleted = true;
    }

    /** Returns the String representation of the task. */
    @Override
    public String toString() {
        return this.description;
    }
}
