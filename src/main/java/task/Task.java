package task;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if the task is done, otherwise it returns a space.
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
        assert(this.getDescription() == "[X] " + this.description);
    }

    /**
     * Sets the `isDone` variable to false.
     */
    public void setUndone() {
        this.isDone = false;
        assert(this.getDescription() == "[ ] " + this.description);
    }

    /**
     * Returns string format of task
     * @return String
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}

