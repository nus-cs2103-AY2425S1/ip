/**
 * Task is an abstract class that contains a description
 * and stores whether it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Creates an entry to a file from a Task
     * @return string representing the task
     */
    public abstract String toFile();

    /**
     * Creates a Task from a given entry in a file
     * @param file
     * @return Task
     */
    public abstract Task createFromFile(String file);

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
