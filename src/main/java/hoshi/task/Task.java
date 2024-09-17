package hoshi.task;

/**
 * This class represents an abstract task with a description and completion status.
 *
 * <p>This class serves as a base class for 3 types of tasks. It maintains a description
 * of said task and whether the task has been completed or not.</p>
 */

public abstract class Task {

    /**
     * Stores the description fo the task.
     */
    private final String description;

    /**
     * Indicates whether the task is done or not.
     */
    private boolean isDone;

    /**
     * Constructs a new instance of Task.
     *
     * @param description String description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new instance of Task.
     *
     * @param description String description of Task.
     * @param isDone Boolean indicating whether Task is done or not.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets status icon of the task.
     *
     * @return isDone String which is either X or blank that indicates the task is done or not.
     */
    public String getStatusIcon() {
        // mark done task with X
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Gets boolean isDone of the task.
     *
     * @return isDone Boolean indicating whether task is done or not
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets whether the task is done or not.
     *
     * @param bool Boolean which indicates whether to mark the task as done or not done.
     */
    public void setIsDone(Boolean bool) {
        this.isDone = bool;
    }

    /**
     * Gets description of the task.
     *
     * @return description String which describes the current task.
     */
    public String getDesc() {
        return this.description;
    }

    /**
     * Returns a string representation of the task.
     *
     * <p>The string includes the status icon and the description of the task.</p>
     *
     * @return A string in the format {"[statusIcon] description"}.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}
