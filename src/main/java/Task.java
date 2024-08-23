/**
 * Represents a task with a description, status, and type.
 * The task can be of type TODO, DEADLINE, or EVENT, represented by the {@link TaskType} enum.
 */
public class Task {

    /**
     * Enumeration of task types. Represents the different types of tasks that can be created.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /** The description of the task. */
    protected String description;

    /** The completion status of the task. */
    protected boolean isDone;

    /** The type of the task (TODO, DEADLINE, EVENT). */
    protected final TaskType type;

    /**
     * Constructs a new {@code Task} with the specified description and type.
     * The task is initially not completed.
     *
     * @param description The description of the task.
     * @param type        The type of the task, as defined by {@link TaskType}.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is completed, otherwise {@code false}.
     */
    public boolean getStatus() {
        return this.isDone;
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
     * Returns a string representation of the task's status.
     * A completed task is marked with an 'X', while an incomplete task is marked with a space.
     *
     * @return A string representing the task's status.
     */
    public String stringStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Toggles the completion status of the task.
     * If the task is completed, it will be marked as not completed and vice versa.
     */
    public void changeStatus() {
        this.isDone = !isDone;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string in the format: "[status] description".
     */
    @Override
    public String toString() {
        return "[" + stringStatus() + "] " + this.description;
    }
}
