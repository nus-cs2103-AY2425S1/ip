package bruno.task;

/**
 * The Task class represents a task with a description and a completion status.
 * It serves as a base class for specific types of tasks.
 */
public class Task {
    private String description;
    private boolean done;

    /**
     * Constructs a Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Constructs a Task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param done The completion status of the task (true if done, false otherwise).
     */
    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
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
        return this.done;
    }

    /**
     * Marks the task as complete.
     */
    public void complete() {
        this.done = true;
    }

    /**
     * Marks the task as not complete.
     */
    public void uncomplete() {
        this.done = false;
    }

    /**
     * Returns a string representation of the Task, including its completion status and description.
     *
     * @return A string that represents the Task.
     */
    @Override
    public String toString() {
        String complete = " ";
        if (this.done) {
            complete = "X";
        }
        return "[" + complete + "] | " + this.description;
    }
}
