package jay.task;

/**
 * Represents a task.
 */
public class Task {
    /**
     * Represents the type of the task.
     */
    public enum Type { ToDo, Deadline, Event, Unknown };
    /**
     * Represents the priority of the task.
     */
    public enum Priority { High, Medium, Low, Unknown };

    private final String description;
    private boolean isDone;
    private Priority priority;

    /**
     * Constructs a task object.
     *
     * @param description The description of the Jay.task.
     * @param isDone      The status of the Jay.task.
     */
    public Task(String description, boolean isDone, Priority priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
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
        return "[" + this.getStatusIcon() + "] " + this.description + " { Priority: " + this.priority + " }";
    }

    private String getSimpleStatusIcon() {
        return this.isDone ? "1" : "0";
    }

    /**
     * Returns a simple format of the task for storage.
     *
     * @return A simple format of the task.
     */
    public String getSimpleFormat() {
        return this.getSimpleStatusIcon() + " | " + this.description + " | " + this.priority;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the priority of the task.
     *
     * @return The priority of the task.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Set the priority of the task.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
