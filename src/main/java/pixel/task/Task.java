package pixel.task;

/**
 * The Task class represents a task with a description and completion status. It
 * is an abstract class that provides common functionality for different types
 * of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object with the given description and completion status.
     *
     * @param description The description of the task.
     * @param done        The completion status of the task ("1" for done, any other
     *                    value for not done).
     */
    public Task(String description, String done) {
        this.description = description;
        if (done.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task ("[X]" for done, "[ ]" for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Toggles the completion status of the task.
     *
     * @return The new completion status of the task.
     */
    public boolean toggleIsDone() {
        this.isDone = !this.isDone;
        return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task, including its type, status icon,
     *         and description.
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + this.getStatusIcon() + " " + this.description;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public abstract String getType();

    /**
     * Returns the data representation of the task.
     *
     * @return The data representation of the task, including its type, completion
     *         status, and description.
     */
    public String getData() {
        int isDoneData = this.isDone ? 1 : 0;
        return this.getType() + "," + isDoneData + "," + this.description;
    }
}
