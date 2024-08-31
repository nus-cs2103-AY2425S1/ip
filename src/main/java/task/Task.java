package task;

/**
 * The Task class is the base class for all Tasks
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a Task
     * @param description String representing the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}