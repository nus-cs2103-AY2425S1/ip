package velma.task;

/**
 * Represents a task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task class
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * @return  A string representing the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes task status to done or undone.
     * @return
     */
    public void changeIsDoneStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns the description of the task.
     * @return string representation of the task.
     */
    public String toString() {

        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the description of the task.
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task.
     * @return
     */
    public boolean getIsDone() {
        return isDone;
    }
}