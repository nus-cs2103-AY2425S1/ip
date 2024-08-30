package task;

/**
 * Represents a task with a name and a completion status.
 * <p>
 * A task can be marked as done or undone, and its name can be retrieved or updated.
 * </p>
 */
public class Task {
    private boolean done = false;
    private String name;

    /**
     * Default constructor that initializes a task with no name and sets its status to undone.
     */
    public Task() {
    }

    /**
     * Constructs a {@code Task} with the specified name.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
    }

    public String toString() {
        String str = "";
        if (done) {
            str += "[X]";
        } else {
            str += "[ ]";
        }
        str += (" " + this.name + "\n");
        return str;
    }

    /**
     * Marks the task as done.
     * This method updates the task's status to completed.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Marks the task as undone.
     * This method updates the task's status to not completed.
     */
    public void markAsUndone() {
        this.done = false;
    }

    /**
     * Returns whether the task is done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise
     */
    public Boolean isDone() {
        return this.done;
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }
}
