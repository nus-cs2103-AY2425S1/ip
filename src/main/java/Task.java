/**
 * Class encapsulating a Task.
 */
public class Task {
    /** String description of the task */
    private String description;
    /** Marks whether the task is done */
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon marking the task's completion status.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark task done with X
    }

    /**
     * Marks the task as done.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void uncomplete() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
