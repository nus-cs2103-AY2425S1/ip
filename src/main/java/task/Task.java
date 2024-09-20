package task;

/**
 * The `Task` class represents a task with a description and completion status, providing methods to mark the
 * task as done or undone.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if the task is done, otherwise it returns a space.
     * 
     * @return Returns "X" if the task is done, otherwise it returns a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of an object.
     * @return Returns the description of an object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the isDone variable to true.
     */
    public void setDone() {
        this.isDone = true;
        assert(this.toString().equals("[X] " + this.description));
    }

    /**
     * Sets the `isDone` variable to false.
     */
    public void setUndone() {
        this.isDone = false;
       assert(this.toString().equals("[ ] " + this.description));
    }

    /**
     * Returns string format of task.
     * 
     * @return String
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
