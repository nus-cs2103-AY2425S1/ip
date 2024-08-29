package task;

/**
 * Represents a task.
 */
public class Task {
    public enum TYPE { TODO, DEADLINE, EVENT, UNKNOWN };

    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    private String getSimpleStatusIcon() {
        return this.isDone ? "1" : "0";
    }

    /**
     * Returns a simple format of the task for storage.
     *
     * @return A simple format of the task.
     */
    public String simpleFormat() {
        return this.getSimpleStatusIcon() + " | " + this.description;
    }
}
