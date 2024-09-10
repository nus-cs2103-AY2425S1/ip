package jay.task;

/**
 * Represents a task.
 */
public class Task {
    /**
     * Represents the type of the task.
     */
    public enum Type { ToDo, Deadline, Event, Unknown };

    private final String description;
    private boolean isDone;

    /**
     * Constructs a task object.
     *
     * @param description The description of the Jay.task.
     * @param isDone      The status of the Jay.task.
     */
    protected Task(String description, boolean isDone) {
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
     * Returns a simple format of the task for Jay.storage.
     *
     * @return A simple format of the Jay.task.
     */
    public String getSimpleFormat() {
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
