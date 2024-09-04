package Jay.task;

/**
 * Represents a Jay.task.
 */
public class Task {
    public enum Type { ToDo, Deadline, Event, Unknown };

    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the Jay.task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the Jay.task as not done.
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
     * Returns a simple format of the Jay.task for Jay.storage.
     *
     * @return A simple format of the Jay.task.
     */
    public String simpleFormat() {
        return this.getSimpleStatusIcon() + " | " + this.description;
    }

    /**
     * Returns the description of the Jay.task.
     *
     * @return The description of the Jay.task.
     */
    public String getDescription() {
        return this.description;
    }
}
