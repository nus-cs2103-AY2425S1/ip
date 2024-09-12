package struggling.task;

/**
 * Task class keep a description and the done status of a user task.
 */
public class Task {
    private final String description;
    private boolean isDone;
    private Priority priority;

    /**
     * Creates a Task object with description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    /**
     * Returns a formatted string representation of Task for display.
     */
    @Override
    public String toString() {
        char priorityLabel = this.priority == Priority.HIGH ? '!' : ' ';
        char isDoneLabel = this.isDone ? 'X' : ' ';
        return String.format("[%s][%s] %s", priorityLabel, isDoneLabel, this.description);
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
        int priorityState = this.priority == Priority.HIGH ? 1 : 0;
        int isDoneState = this.isDone ? 1 : 0;
        return String.format("%d | %d | %s", priorityState, isDoneState, this.description);
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Set the priority of this task to HIGH.
     */
    public void setPriorityHigh() {
        this.priority = Priority.HIGH;
    }

    /**
     * Set the priority of this task to LOW.
     */
    public void setPriorityLow() {
        this.priority = Priority.LOW;
    }

    private enum Priority {
        HIGH, LOW
    }
}
