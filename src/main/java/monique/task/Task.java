package monique.task;

/**
 * The <code>Task</code> class represents a general task with a description and a completion status.
 * This is an abstract class, meant to be extended by specific types of tasks such as
 * * <code>Deadline</code> and <code>Event</code>.
 */
public abstract class Task implements java.io.Serializable {
    private final String description;
    private final boolean isComplete;

    /**
     * Constructs a new <code>Task</code> object with the specified description and sets the task as incomplete.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }


    /**
     * Constructs a new <code>Task</code> object with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isComplete The completion status of the task.
     */
    public Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    /**
     * Constructs a new <code>Task</code> object with an empty description and sets the task as incomplete.
     */
    public Task() {
        this("");
    }

    /**
     * Marks the <code>Task</code> as complete and returns a new instance of the task with the updated status.
     * This is an abstract method and must be implemented by subclasses.
     *
     * @return A new <code>Task</code> object with the status marked as complete.
     */
    public abstract Task mark();

    /**
     * Unmarks the <code>Task</code> as incomplete and returns a new instance of the task with the updated status.
     * This is an abstract method and must be implemented by subclasses.
     *
     * @return A new <code>Task</code> object with the status marked as incomplete.
     */
    public abstract Task unmark();

    /**
     * Returns the description of the <code>Task</code>.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status of the <code>Task</code>.
     *
     * @return True if the task is complete, false otherwise.
     */
    public boolean isComplete() {
        return isComplete;
    }
}
