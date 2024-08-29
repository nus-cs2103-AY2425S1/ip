package NextGPT.task;

/**
 * Class for tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        isDone = true;
    }
    /**
     * Marks task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    public boolean isDone(){return this.isDone;}

    public String toString() {
        return "[" + (isDone? "X":" ") + "] " + this.description;
    }


}
