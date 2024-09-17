package ollie.task;

/**
 * Represents a task. A task contains a description and a boolean value
 * which represents the task's completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object which represents a general task with a description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets task to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets task to undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the formatted string for easy parsing and writing into file (database).
     *
     * @return Formatted String.
     */
    public String getFormattedString() {
        return String.format("%d | %s", isDone ? 1 : 0, this.description);
    }

    /**
     * Returns true if description contains the string input
     */
    public boolean doesDescContain(String s) {
        return this.description.contains(s);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
