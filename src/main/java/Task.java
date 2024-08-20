
/**
 * This class represents an abstract task with a description and completion status.
 *
 * <p>This class serves as a base class for 3 types of tasks. It maintains a description
 * of said task and whether the task has been completed or not.</p>
 */

public abstract class Task {

    protected String description;
    protected boolean isDone;


    /**
     * Constructs a new instance of Task.
     *
     * @param description String description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setIsDone(Boolean bool) {
        this.isDone = bool;
    }

    public String getDesc() {
        return description;
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
