package bruno.task;

/**
 * The Task class represents a task with a description and a completion status.
 * It serves as a base class for specific types of tasks.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task (true if done, false otherwise).
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
     * Returns the completion status of the task.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as complete.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Marks the task as not complete.
     */
    public void uncomplete() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String complete = " ";
        if (this.isDone) {
            complete = "X";
        }
        return "[" + complete + "] | " + this.description;
    }
}
