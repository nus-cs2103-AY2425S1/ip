package tasks;

/**
 * Class encapsulating taskis
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Checks the status of the task.
     *
     * @return true if the task is done else false.
     */
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.description;
    }
}
